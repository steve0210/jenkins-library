def database() {
  fileOperations([fileCreateOperation(fileContent: '''# MySQL. Versions 5.5.8 and up are supported.
default: &default
  adapter: sqlite3
  pool: 5
  timeout: 5000

production:
  <<: *default
  database: db/development.sqlite3
''', fileName: 'config/database.yml')])
}

def secrets() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  secret_key_base: <%= ENV["SECRET_KEY_BASE"] %>

''', fileName: 'config/secrets.yml')])
}

def webpacker() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
default: &default
  source_path: app/javascript
  source_entry_path: packs
  public_output_path: packs

  extensions:
    - .coffee
    - .erb
    - .js
    - .jsx
    - .ts
    - .vue
    - .sass
    - .scss
    - .css
    - .png
    - .svg
    - .gif
    - .jpeg
    - .jpg

development:
  <<: *default

  dev_server:
    host: localhost
    port: 9000
    https: false

production:
  <<: *default
''', fileName: 'config/webpacker.yml')])
}

def servers() {
  fileOperations([fileCreateOperation(fileContent: '''# Be sure to restart your server when you modify this file.
production:
  labis_legacy_server:
    host: cpl.labis.net
    port:
  labis_api_server:
    host: api.labis.net
    port:
  barcode_server:
    host: 10.212.16.141
    port: 8200
  cbl_api_server:
    host: labflowapidemo.labis.net
  cbl_legacy_server:
    host: labflowdemo.labis.net
''', fileName: 'config/servers.yml')])
}
