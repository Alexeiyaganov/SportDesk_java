<#include "security.ftl">
<#import "pager.ftl" as p>

<@p.pager url page/>

<div class='color container-fluid ' >
    <br>
    <div class="row align-items-center border border-secondary" >
        <div class="col"><span>Название</span></div>
        <div class="col"><span>Превью</span></div>
        <div class="col"><span>Дата и время</span></div>
        <div class="col"><span>Описание мероприятия</span></div>
        <div class="col"><i>Тэг</i></div>
        <div class="col"><span>Подробное описание</span><</div>
        <div class="col"><span>Последнее редактирование</span></div>
        <div class="col"><span>Организатор</span></div>
        <div class="col"></div>

    </div>
    <#list page.content as message>
        <div class="row align-items-center border border-secondary" <#if message.filename??> style="background: aquamarine;" </#if>>
            <div class="col"><span>${message.name}</span></div>
            <div class="col"><#if message.filename??><a href="/img/${message.filename}"><img src="/img/${message.filename}" class="img-fluid "  style="max-height: 50px"></a></#if></div>
            <div class="col"><span>${message.date}</span></div>
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
<@p.pager url page/>