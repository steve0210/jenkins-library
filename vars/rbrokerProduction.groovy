def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
labis_cpl_production:
  adapter: mysql2
  database: lmo_tpcm_production
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004p.us.int.sonichealthcare
  port: 3307
  reconnect: true

tps_cpl_production:
  adapter: sqlserver
  mode: odbc
  dsn: TPSDev
  username: nobody
  password: password
''', fileName: 'lib/Data/database.yml')])

}

def config() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
errors:
  path: /mnt/errors/

cpl_production:
  mercury:
    host: 10.212.148.40
    port: 3003
  software_interfaces:
    host: 10.212.148.40
    port: 3005
''', fileName: 'lib/Data/config.yml')])
}
