def vendor() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
production:
  host: cplsceris.us.int.sonichealthcare
  port: 8080
  interface: LABISAPI
  filter: ACN
  userid: LabIS
  password: ECML@6!2
''', fileName: 'config/vendor.yml')])
}
