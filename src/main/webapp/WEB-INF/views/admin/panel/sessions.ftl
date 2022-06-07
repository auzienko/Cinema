<#import "../../ui.ftl" as ui/>
<@ui.header title="🌅 Sessions panel"/>
<form method="post" action="sessions">
    <div class="container">
        <table>
            <tr>
                <td>
                    <label for="movie">Enter film's id:</label>
                    <input name="movie" type="number" value="0" min="0" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="hall">Enter hall's id:</label>
                    <input name="hall" type="number" value="0" min="0" max="42" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="dateTime">Enter session date and time</label>
                    <input name="dateTime" type="datetime-local" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="cost">Enter ticket cost:</label>
                    <input name="cost" type="number" value="0" min="0" max="1000" required/>
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