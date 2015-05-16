<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'trip.label', default: 'Trip')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-trip" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-trip" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="id" title="${message(code: 'person.firstName.label', default: 'Id')}" />
                    <g:sortableColumn property="startDate" title="${message(code: 'person.lastName.label', default: 'Start Date')}" />
                    <g:sortableColumn property="endDate" title="${message(code: 'person.dateOfBirth.label', default: 'End Date')}" />
                    <g:sortableColumn property="endDate" title="${message(code: 'person.dateOfBirth.label', default: 'Details')}" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${tripList}" status="i" var="personInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:link action="show" id="${personInstance.id}">${fieldValue(bean: personInstance, field: "id")}</g:link></td>
                        <td><g:formatDate date="${personInstance.startDate}" /></td>
                        <td><g:formatDate date="${personInstance.endDate}" /></td>
                        <td><g:link controller="obdReading" action="index" params="[tripId:personInstance.tripId]" id="${personInstance.tripId}">details</g:link></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${tripCount ?: 0}" />
            </div>
        </div>
    </body>
</html>