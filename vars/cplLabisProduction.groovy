def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
production:
  adapter: mysql2
  database: lmo_tpcm_production
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004p.us.int.sonichealthcare
  port: 3307
  reconnect: true

mercator_production:
  adapter: mysql2
  database: mercator
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>
  host: ussigatxmys004p.us.int.sonichealthcare
  port: 3307
  reconnect: true
''', fileName: 'config/database.yml')])

}

def secrets() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  secret: <%= ENV["SECRET_KEY_BASE"] %>

''', fileName: 'config/secrets.yml')])
}

def queue_server() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  host: rabbitmqapp
  username: labis
  password: L@b1$
  enabled: false

''', fileName: 'config/queue_server.yml')])
}

def servers() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  slide_server:
    host: labeler-prod
    port: 3000
  mongo_server:
    host: 10.214.5.134
    port: 8088
  req_viewer:
    host: reqimage-prod
    port: 3000
  redis_server:
    host: USSIGATXRED003P.US.INT.SONICHEALTHCARE
    port: 6379
  labis_api:
    host: apipli.labis.net
  labis_v2:
    host: v2pli.labis.net
  labis_server:
    host: v2pli.labis.net
  device_server:
    host: localhost
    port: 9999
  ssrs_server:
    host: https://ssrstest.labis.net
    folder: LABIS Reports
    user: reportuser
    password: S0nic123
''', fileName: 'config/servers.yml')])
}

def memcached() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
defaults:
  readonly: false
  urlencode: false
  c_threshold: 10000
  compression: true
  debug: false
  fragments: false
  memory: 256
  expires: 89400
  timeout: 3

production:
  namespace: "mem-production"
  memory: 256
  benchmarking: false
  servers: localhost:11211
''', fileName: 'config/memcached.yml')])
}
