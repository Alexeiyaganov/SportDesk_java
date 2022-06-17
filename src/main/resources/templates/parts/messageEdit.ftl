<#include "security.ftl">
<#if user??>
    <a class="btn btn-primary" data-toggle="collapse" href="#messageEdit" role="button" aria-expanded="false" aria-controls="collapseExample">
        Добавить мероприятие
    </a>

    <div class="collapse <#if message??>show</#if>" id="messageEdit">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.name}</#if>" name="name" placeholder="Введите название" />
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}

                        </div>
                    </#if>
                </div>

                <div class="form-group ${(dateError??)?string('is-invalid', '')}">
                    <input type="datetime-local" class="form-control " value="<#if message??>${message.setLocalDateTime()}</#if>" name="localDateTime" />
                    <#if dateError??>
                        <div class="invalid-feedback">
                            ${dateError}

                        </div>
                    </#if>

                </div>


                <div class="form-group">
                    <input type="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.text}</#if>" name="text" placeholder="Введите описание мероприятия" />
                    <#if textError??>
                        <div class="invalid-feedback">
                            ${textError}

                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control"
                           value="<#if message??>${message.tag}</#if>" name="tag" placeholder="Тэг">
                    <#if tagError??>
                        <div class="invalid-feedback">
                            ${tagError}

                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <div id="map" style="width: 600px; height: 400px"></div>
                    <input type="hidden" id="xpoint" value="<#if message??>${message.latitude}</#if>">
                    <input type="hidden" id="yypoint" value="<#if message??>${message.longitude}</#if>">
                    <input type="hidden" id="location_point" value="<#if message??>${message.location}</#if>">
                    <input type="hidden" name="location_name" id="name_point" value="<#if message??>${message.location}</#if>" class="form-input">
                    <input type="hidden" name="location" id="yandex_point" class="form-input">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
</#if>
<br>