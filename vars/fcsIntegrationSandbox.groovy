def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
cpl_production:
  adapter: sqlite3
  database: db/cpldemo.sqlite3
  pool: 5
  timeout: 5000

labis_cpl_production:
  adapter: mysql2
  database: cpl_enterprise_demo
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004t.us.int.sonichealthcare
  port: 3307
  reconnect: true
''', fileName: 'config/database.yml')])
}

def config() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
cpl_production:
  emailer:
    server: 'AppMailer'
    to: 'bugs@cblpath.com'

  new_xml_location: "/mnt/new_fcs_results"
  processed_xml_location: "/mnt/processed_fcs_results"
  client_id: 1
  embedded_files: true
''', fileName: 'config/config.yml')])
}
