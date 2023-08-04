def vendor() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
production:
  host: cplsceris.us.int.sonichealthcare
  port: 8080
  interface: LABISAPI
  filter: ACN
  userid: LabIS
  password:  <%= ENV["SCERIS_USER_PASSWORD"] %>
''', fileName: 'config/vendor.yml')])
}
