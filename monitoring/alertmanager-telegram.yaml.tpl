apiVersion: monitoring.coreos.com/v1alpha1
kind: AlertmanagerConfig
metadata:
  name: tictactoe-telegram
  namespace: default
spec:
  route:
    receiver: telegram
    groupBy:
      - alertname
      - namespace
    groupWait: 10s
    groupInterval: 1m
    repeatInterval: 4h
    matchers:
      - name: alertname
        matchType: =~
        value: "TicTacToe.*"
  receivers:
    - name: telegram
      telegramConfigs:
        - botToken:
            name: telegram-bot
            key: bot-token
          chatID: ${TELEGRAM_CHAT_ID}
          sendResolved: true
          httpConfig:
            proxyURL: "${TELEGRAM_PROXY_URL}"
