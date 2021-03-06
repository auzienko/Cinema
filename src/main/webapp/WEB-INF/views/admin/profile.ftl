<#ftl encoding="utf-8">
<#import "../ui.ftl" as ui/>
<@ui.header title="⚙️ Cinema Profile Page"/>
<#if error?has_content>
    <h1 style="text-align: center"><b>${error}</b></h1>
</#if>
<table style="height: 288px; width: 60%; border-collapse: collapse; margin: 0px auto; ;" border="0" align="center">
    <tbody>
    <tr style="height: 270px;">
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">
                        <img src="${avatar}" width="150"/>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <form action="profile" enctype="multipart/form-data"
                              method="post">
                            <input type="file" name="avatarFile" class="btn" accept="image/*" required>
                            <button type="submit" class="btn">Upload</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
        <td style="width: 50%; height: 270px;">
            <table style="height: 100%; width: 100%; border-collapse: collapse;" border="0" cellspacing="0"
                   cellpadding="0">
                <tbody>
                <tr>
                    <td style="width: 100%;">

                        <h1>${user.name}</h1>
                        <p>"${user.email}"</p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 100%;">
                        <table style="width: 100%; border-collapse: collapse;" border="1">
                            <tbody>
                            <#if authHistory?has_content>
                                <div class="container">
                                    <table class="minimalistBlack">
                                        <thead>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>IP</th>
                                        </thead>
                                        <#list authHistory as row>
                                            <tr>
                                                <td>${row.toDateTimeString("MMMM dd, yyyy")}</td>
                                                <td>${row.toDateTimeString("HH:mm")}</td>
                                                <td>${row.ip}</td>
                                            </tr>
                                        </#list>
                                    </table>
                                </div>
                            </#if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>
        </td>
    </tr>
    </tbody>
</table>
    <tbody>
    <#if avatarHistory?has_content>
        <div class="container">
            <table class="minimalistBlack">
                <thead>
                <th>File</th>
                <th>Size</th>
                <th>Mime</th>
                </thead>
                <#list avatarHistory as row>
                    <tr>
                        <td> <a href="../../images/avatar/${row.fileNameUUID}"  target="_blank">${row.fileName}</a></td>
                        <td>${row.size}</td>
                        <td>${row.mime}</td>
                    </tr>
                </#list>
            </table>
        </div>
    </#if>
    </tbody>
</table>
<@ui.tail/>