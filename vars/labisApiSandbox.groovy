def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
default: &default
  adapter: postgresql
  encoding: unicode
  password: <%= ENV['LABIS_POSTGRES_DATABASE_CREDS'].split(':')[1] %>
  pool: <%= ENV.fetch("RAILS_MAX_THREADS") { 5 } %>

legacy_default: &legacy_default
  adapter: mysql2
  password: <%= ENV["MYSQL_USER_CREDS"].split(':')[1] %>

production:
  <<: *default
  database: labis_cpl_demo
  host: 10.212.148.45
  username: <%= ENV["LABIS_POSTGRES_DATABASE_CREDS"].split(':')[0] %>

legacy_production:
  <<: *legacy_default
  database: cpl_enterprise_demo
  username: <%= ENV["MYSQL_USER_CREDS"].split(':')[0] %>
  host: ussigatxmys004t.us.int.sonichealthcare
  port: 3307
''', fileName: 'config/database.yml')])
}

def secrets() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  secret: <%= ENV["SECRET_KEY_BASE"] %>

''', fileName: 'config/secrets.yml')])
}

def cable() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  adapter: redis
  url: redis://localhost:6379/1
''', fileName: 'config/cable.yml')])
}

def servers() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  slide_server:
    host: 10.212.148.40
    port: 8081
  redis_server:
    host: 10.212.148.40
    port: 6379
  legacy_server:
    host: cpldemo.labis.net
    port:
''', fileName: 'config/servers.yml')])
}
