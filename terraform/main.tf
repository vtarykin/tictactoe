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

          resources {}
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
