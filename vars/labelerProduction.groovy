def servers() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
  production:
    barcode:
      host: localhost
      port: 8080
''', fileName: 'config/servers.yml')])
}
