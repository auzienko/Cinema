<#import "../../ui.ftl" as ui/>
<@ui.header title="🍿 Halls panel"/>
<form method="post" action="halls">
    <div class="container">
        <table>
            <tr>
                <td>
                    <label for="serialNumber">Enter hall's serial number:</label>
                    <input name="serialNumber" type="number" value="0" min="0" max="42" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="seats">Enter count of seats:</label>
                    <input name="seats" type="number" value="0" min="0" max="420" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Add hall</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<@ui.tail/>