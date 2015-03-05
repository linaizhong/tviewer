// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
/******************************************************************************\
 *  CONFIG MANAGEMENT
 \******************************************************************************/
def ENV_NAME = "TVIEWER_CONFIG"
def default_config = "/data/${appName}/config/${appName}-config.properties"
if(!grails.config.locations || !(grails.config.locations instanceof List)) {
    grails.config.locations = []
}
reloadable.cfgs = ["file:/data/${appName}/config/${appName}-config.properties"]
if(System.getenv(ENV_NAME) && new File(System.getenv(ENV_NAME)).exists()) {
    println "[TVIEWER] Including configuration file specified in environment: " + System.getenv(ENV_NAME);
    grails.config.locations = ["file:" + System.getenv(ENV_NAME)]
} else if(System.getProperty(ENV_NAME) && new File(System.getProperty(ENV_NAME)).exists()) {
    println "[TVIEWER] Including configuration file specified on command line: " + System.getProperty(ENV_NAME);
    grails.config.locations = ["file:" + System.getProperty(ENV_NAME)]
} else if(new File(default_config).exists()) {
    println "[TVIEWER] Including default configuration file: " + default_config;
    def loc = ["file:" + default_config]
    println "[TVIEWER] >> loc = " + loc
    grails.config.locations = loc
    println "[TVIEWER] grails.config.locations = " + grails.config.locations
} else {
    println "[TVIEWER] No external configuration file defined."
}


/******************************************************************************\
 *  EXTERNAL SERVERS
 \******************************************************************************/
if (!bie.baseURL) {
    bie.baseURL = "http://bie.ala.org.au"
}
if (!bie.searchPath) {
    bie.searchPath = "/search"
}
if (!biocache.baseURL) {
    biocache.baseURL = "http://biocache.ala.org.au"
}
if (!spatial.baseURL) {
    spatial.baseURL = "http://spatial-dev.ala.org.au"
}
if (!ala.baseURL) {
    ala.baseURL = "http://www.ala.org.au"
}
// spatial services
if (!spatial.wms.url) {
    spatial.wms.url = spatial.baseURL + "/geoserver/ALA/wms?"
}
if (!spatial.wms.cache.url) {
    spatial.wms.cache.url = spatial.baseURL + "/geoserver/gwc/service/wms?"
}
if (!spatial.layers.service.url) {
    spatial.layers.service.url = spatial.baseURL + "/layers-service"
}
if (!headerAndFooter.baseURL) {
    headerAndFooter.baseURL = "http://www2.ala.org.au/commonui"
}
/******************************************************************************\
 *  APP CONFIG
 \******************************************************************************/
image.source.dataResourceUid = 'dr660'

/******************************************************************************\
 *  SECURITY
 \******************************************************************************/
if (!security.cas.urlPattern) {
    security.cas.urlPattern = ""
}
if (!security.cas.loginUrl) {
    security.cas.loginUrl = "https://auth.ala.org.au/cas/login"
}
if (!security.cas.logoutUrl) {
    security.cas.logoutUrl = "https://auth.ala.org.au/cas/logout"
}

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
grails.resources.adhoc.patterns = ['/images/*', '/data/images/*', '/css/*', '/js/*', '/plugins/*']

//grails.urlmapping.cache.maxsize = 1000

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// whether to install the java.util.logging bridge for sl4j. Disable for AppEngine!
grails.logging.jul.usebridge = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://taxaexp.ala.org.au"
        distribution.image.cache = "http://fish.ala.org.au/data/images"
        if (!results.cache.baseUrl) {
            results.cache.baseUrl = "http://fish.ala.org.au/results"
        }
        if (!distribution.search.baseUrl) {
            distribution.search.baseUrl = "http://fish.ala.org.au/search"
        }
    }
    development {
        grails.host = "152.83.206.10"
        //grails.host = "woodfired.ala.org.au"
        //grails.serverURL = "http://${grails.host}:8082/${appName}"
        grails.serverURL = "http://${grails.host}:8090/${appName}"
        //distribution.image.cache = "http://${grails.host}/data/expert/images"
        //distribution.image.cache = "http://${grails.host}:8082/data/images"
        distribution.image.cache = "http://${grails.host}:8090/tviewer/data/images"
        results.cache.baseUrl = "http://${grails.host}:8080/expert/results"
        distribution.search.baseUrl = "http://${grails.host}:8080/expert/search"
    }
    test {
        grails.host = "130.56.248.132"
        grails.serverURL = "http://${grails.host}/${appName}"
        distribution.image.cache = "http://${grails.host}/data/images"
        results.cache.baseUrl = "http://${grails.host}/expert/results"
        distribution.search.baseUrl = "http://${grails.host}/expert/search"

//        grails.host = "spatial-dev.ala.org.au"
//        grails.serverURL = "http://${grails.host}/${appName}"
//        distribution.image.cache = "http://${grails.host}/data/images"
//        results.cache.baseUrl = "http://${grails.host}/expert/results"
//        distribution.search.baseUrl = "http://${grails.host}/expert/search"
    }

}

def catalinaBase = System.properties.getProperty('catalina.base')
if (!catalinaBase) catalinaBase = '.'   // just in case
def logDirectory = "${catalinaBase}/logs"

// log4j configuration
log4j = {
    appenders {
        environments {
            production {
                rollingFile name: "tviewer",
                    maxFileSize: 104857600,
                    file: "${logDirectory}/tviewer.log",
                    threshold: org.apache.log4j.Level.WARN,
                    layout: pattern(conversionPattern: "%d [%c{1}]  %m%n")
                rollingFile name: "stacktrace", maxFileSize: 1024, file: "${logDirectory}/tviewer-stacktrace.log"
            }
            development{
                console name: "stdout", layout: pattern(conversionPattern: "%d [%c{1}]  %m%n"), threshold: org.apache.log4j.Level.DEBUG
            }
        }
    }

    root {
        debug  'tviewer'
    }

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
           'org.codehaus.groovy.grails.web.pages', //  GSP
           'org.codehaus.groovy.grails.web.sitemesh', //  layouts
	         'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping', // URL mapping
	         'org.codehaus.groovy.grails.commons', // core / classloading
	         'org.codehaus.groovy.grails.plugins', // plugins
           'org.springframework.jdbc',
           'org.springframework.transaction',
           'org.codehaus.groovy',
           'org.grails',
           'org.apache',
           'grails.spring',
           'grails.util.GrailsUtil',
           'net.sf.ehcache'
           'grails.app.service.org.grails.plugin.resource'
           'grails.app.service'
           'org.ala'
           'au.org.ala'
           'grails.app.service.org.grails.plugin.resource.ResourceTagLib'
    debug  'au.org.ala'
}