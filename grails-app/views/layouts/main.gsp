<!doctype html>
<html lang="en" class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <asset:stylesheet src="application.css"/>
        <asset:javascript src="application.js"/>

        <g:layoutHead/>
    </head>
    <body>
        <div id="grailsLogo" role="banner"><a>乐驾</a></div>
        <g:layoutBody/>
        <div class="footer" role="contentinfo">
            <g:if env="development">
                <ul>
                    <li class="controller"><g:link controller="debug">DEBUG</g:link></li>
                </ul>
            </g:if>
        </div>
        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
    </body>
</html>
