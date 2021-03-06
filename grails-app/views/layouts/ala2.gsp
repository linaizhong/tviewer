<!DOCTYPE html>
<html>
<head>
    <title><g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="http://ala.org.au/wp-content/themes/ala2011/style.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="http://ala.org.au/wp-content/themes/ala2011/css/wp-styles.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="http://ala.org.au/wp-content/themes/ala2011/css/buttons.css" type="text/css" media="screen" />
    <link rel="icon" type="image/x-icon" href="http://ala.org.au/wp-content/themes/ala2011/images/favicon.ico" />
    <link rel="shortcut icon" type="image/x-icon" href="http://ala.org.au/wp-content/themes/ala2011/images/favicon.ico" />
    <link rel="stylesheet" type="text/css" media="screen" href="http://ala.org.au/wp-content/themes/ala2011/css/jquery.autocomplete.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="http://ala.org.au/wp-content/themes/ala2011/css/search.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="http://ala.org.au/wp-content/themes/ala2011/css/skin.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="http://ala.org.au/wp-content/themes/ala2011/css/sf.css" />
    <script language="JavaScript" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <r:require module="html5" />
    <g:layoutHead />
    <script language="JavaScript" type="text/javascript" src="http://ala.org.au/wp-content/themes/ala2011/scripts/superfish/superfish.js"></script>
    <script language="JavaScript" type="text/javascript" src="http://ala.org.au/wp-content/themes/ala2011/scripts/jquery.autocomplete.js"></script>
    <script language="JavaScript" type="text/javascript" src="http://ala.org.au/wp-content/themes/ala2011/scripts/uservoice.js"></script>
    <r:script type="text/javascript">

        // initialise plugins

        jQuery(function(){
            jQuery('ul.sf').superfish( {
                delay:500,
                autoArrows:false,
                dropShadows:false
            });

            jQuery("form#search-form input#search").autocomplete('http://bie.ala.org.au/search/auto.jsonp', {
                extraParams: {limit: 100},
                dataType: 'jsonp',
                parse: function(data) {
                    var rows = new Array();
                    data = data.autoCompleteList;
                    for(var i=0; i<data.length; i++){
                        rows[i] = {
                            data:data[i],
                            value: data[i].matchedNames[0],
                            result: data[i].matchedNames[0]
                        };
                    }
                    return rows;
                },
                matchSubset: false,
                formatItem: function(row, i, n) {
                    return row.matchedNames[0];
                },
                cacheLength: 10,
                minChars: 3,
                scroll: false,
                max: 10,
                selectFirst: false
            });
            jQuery("form#search-inpage input#search").autocomplete('http://bie.ala.org.au/search/auto.jsonp', {
                extraParams: {limit: 100},
                dataType: 'jsonp',
                parse: function(data) {
                    var rows = new Array();
                    data = data.autoCompleteList;
                    for(var i=0; i<data.length; i++){
                        rows[i] = {
                            data:data[i],
                            value: data[i].matchedNames[0],
                            result: data[i].matchedNames[0]
                        };
                    }
                    return rows;
                },
                matchSubset: false,
                formatItem: function(row, i, n) {
                    return row.matchedNames[0];
                },
                cacheLength: 10,
                minChars: 3,
                scroll: false,
                max: 10,
                selectFirst: false
            });
        });
    </r:script>
    <r:require module="html5" />
    <r:layoutResources/>
</head>
<body class="${pageProperty(name:'body.class')} fish">

<hf:banner logoutUrl="${grailsApplication.config.grails.serverURL}/taxon/logout"/>

<g:layoutBody />

<hf:footer/>

<r:script type="text/javascript">
    // show warning if using IE6
    if ($.browser.msie && $.browser.version.slice(0,1) == '6') {
        $('#header').prepend($('<div style="text-align:center;color:red;">WARNING: This page is not compatible with IE6.' +
                ' Many functions will still work but layout and image transparency will be disrupted.</div>'));
    }
</r:script>
<script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script>
    var pageTracker = _gat._getTracker("UA-4355440-1");
    pageTracker._initData();
    pageTracker._trackPageview();

    // show warning if using IE6
    if ($.browser && $.browser.msie && $.browser.version.slice(0,1) == '6') {
        $('#header').prepend($('<div style="text-align:center;color:red;">WARNING: This page is not compatible with IE6.' +
                ' Many functions will still work but layout and image transparency will be disrupted.</div>'));
    }
</script>
<r:layoutResources/>
</body>
</html>