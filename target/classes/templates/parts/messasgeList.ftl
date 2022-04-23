<#include "security.ftl">

<div class='color container-fluid ' >
    <#list messages as message>
        <div class="row align-items-center border border-secondary" <#if message.filename??> style="background: gainsboro; height: 150px" </#if>>
            <div class="col"><h5>Название карточки</h5></div>
            <div class="col"><#if message.filename??><a href="/img/${message.filename}"><img src="/img/${message.filename}" class="img-thumbnail rounded-circle" style="font-size: 3em;"></a></#if></div>
            <div class="col"><span>${message.text}</span></div>
            <div class="col"><i>#${message.tag}</i></div>
            <div class="col"><a class="btn btn-primary" href="/user-messages/${message.authorId}">Подробнее</a></div>
            <div class="col"><small class="text-muted">Last updated 3 mins ago</small></div>
            <div class="col"><a href="/user-messages/${message.authorId}">${message.authorName}</a></div>
            <div class="col"><#if message.authorId == currentUserId><a class="btn btn-primary" href="/user-messages/${message.authorId}?message=${message.id}">Редактировать</a></#if></div>

        </div>
    <#else >
        Нет новостей
    </#list>
</div>