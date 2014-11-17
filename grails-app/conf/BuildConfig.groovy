grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.groupId = "au.org.ala"

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        mavenRepo "http://nexus.ala.org.au/content/groups/public/"
        mavenRepo "http://maven.ala.org.au/repository"

    }
    dependencies {
        compile group: 'au.org.ala',
            name: 'ala-cas-client',
            version:'2.1-SNAPSHOT',
            transitive:false
        compile 'org.jasig.cas.client:cas-client-core:3.1.12'
    }
    plugins {
        //runtime ":jquery:1.8.0"
        runtime ":resources:1.2.8"
        runtime ":rest:0.8"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:7.0.54"

        compile ':cache:1.1.1'
        compile ":cache-ehcache:1.0.0"
    }
}
