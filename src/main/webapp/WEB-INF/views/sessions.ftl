<#import "ui.ftl" as ui/>
<@ui.header title="Welcome to Cinema"/>
<form action="/sessions/search" method="get">
    <div class="container">
        <input type="text" name="filmName" placeholder="Enter a film name"/>
        <button type="submit" class="btn">🔎 find</button>
    </div>
</form>
<@ui.tail/>
