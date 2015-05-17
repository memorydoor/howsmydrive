<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'obdReading.label', default: 'ObdReading')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-obdReading" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-obdReading" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="latitude" title="${message(code: 'person.firstName.label', default: 'Latitude')}" />
                    <g:sortableColumn property="startDate" title="${message(code: 'person.lastName.label', default: 'Longitude')}" />
                    <g:sortableColumn property="endDate" title="${message(code: 'person.dateOfBirth.label', default: 'Speed')}" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${obdReadingList}" status="i" var="obdReading">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${fieldValue(bean: obdReading, field: "latitude")}</td>
                        <td>${fieldValue(bean: obdReading, field: "longitude")}</td>
                        <td>${fieldValue(bean: obdReading, field: "speed")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${obdReadingCount ?: 0}" />
            </div>
        </div>
    </body>
</html>