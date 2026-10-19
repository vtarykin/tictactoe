terraform {
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 3.0"
    }
  }
}

provider "kubernetes" {
  config_path = "~/.kube/config"
}

resource "kubernetes_deployment_v1" "tictactoe" {
  metadata {
    name      = "tictactoe"
    namespace = "default"

    labels = {
      "app.kubernetes.io/instance"   = "tictactoe"
      "app.kubernetes.io/managed-by" = "Helm"
      "app.kubernetes.io/name"       = "tictactoe"
      "app.kubernetes.io/version"    = "1.16.0"
      "helm.sh/chart"                = "tictactoe-0.1.0"
    }
  }

  lifecycle {
    ignore_changes = [
      metadata[0].annotations["meta.helm.sh/release-name"],
      metadata[0].annotations["meta.helm.sh/release-namespace"],
      spec[0].template[0].spec[0].container[0].image,
    ]
  }

  spec {
    replicas = 1

    selector {
      match_labels = {
        "app.kubernetes.io/instance" = "tictactoe"
        "app.kubernetes.io/name"     = "tictactoe"
      }
    }

    template {
      metadata {
        labels = {
          "app.kubernetes.io/instance"   = "tictactoe"
          "app.kubernetes.io/managed-by" = "Helm"
          "app.kubernetes.io/name"       = "tictactoe"
          "app.kubernetes.io/version"    = "1.16.0"
          "helm.sh/chart"                = "tictactoe-0.1.0"
        }
      }

      spec {
        automount_service_account_token = false
        enable_service_links            = false

        container {
          name              = "tictactoe"
          image             = "ghcr.io/vtarykin/tictactoe:c04b186cc5d1a1a0d67004d727f206dee91f0179"
          image_pull_policy = "IfNotPresent"

          port {
            name           = "http"
            container_port = 8080
            protocol       = "TCP"
          }

          resources {
            requests = {
              cpu    = "100m"
              memory = "256Mi"
            }

            limits = {
              cpu    = "500m"
              memory = "512Mi"
            }
          }

          readiness_probe {
            http_get {
              path = "/"
              port = 8080
            }

            initial_delay_seconds = 15
            period_seconds        = 5
            timeout_seconds       = 2
            failure_threshold     = 6
          }

        }
      }
    }
  }
}

resource "kubernetes_service_v1" "tictactoe" {

  metadata {
    name      = "tictactoe"
    namespace = "default"

    labels = {
      "app.kubernetes.io/instance"   = "tictactoe"
      "app.kubernetes.io/managed-by" = "Helm"
      "app.kubernetes.io/name"       = "tictactoe"
      "app.kubernetes.io/version"    = "1.16.0"
      "helm.sh/chart"                = "tictactoe-0.1.0"
    }
  }

  spec {
    selector = {
      "app.kubernetes.io/instance" = "tictactoe"
      "app.kubernetes.io/name"     = "tictactoe"
    }

    port {
      name        = "http"
      port        = 8080
      target_port = "http"
      protocol    = "TCP"
    }

    type = "NodePort"
  }
}

resource "kubernetes_horizontal_pod_autoscaler_v2" "tictactoe" {

  metadata {
    name      = "tictactoe"
    namespace = "default"
  }

  spec {
    min_replicas = 1
    max_replicas = 3

    scale_target_ref {
      api_version = "apps/v1"
      kind        = "Deployment"
      name        = "tictactoe"
    }

    metric {
      type = "Resource"

      resource {
        name = "cpu"

        target {
          type                = "Utilization"
          average_utilization = 60
        }
      }
    }

    behavior {
      scale_up {
        stabilization_window_seconds = 0
        select_policy                = "Max"

        policy {
          type           = "Pods"
          value          = 1
          period_seconds = 60
        }
      }

      scale_down {
        stabilization_window_seconds = 300
        select_policy                = "Min"

        policy {
          type           = "Pods"
          value          = 1
          period_seconds = 60
        }
      }
    }
  }
}
