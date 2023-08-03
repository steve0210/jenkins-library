def servers() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
  production:
    barcode:
      host: 10.212.148.40
      port: 8200
''', fileName: 'config/servers.yml')])
}
