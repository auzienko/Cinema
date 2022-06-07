<#macro header title>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link href="/static/style/main.css" rel="stylesheet">
</head>
<body>
<div class="columns">
    <div class="column_main_wrapper">
        <div class="column_main">
        <H1 style="text-align: center">${title}</H1>
        <hr>
</#macro>

<#macro tail>
        </div>
    </div>
    <div class="column_left"></div>
    <div class="column_right"></div>
</div>
</body>
</html>
</#macro>