<#import "../../ui.ftl" as ui/>
<@ui.header title="🎞 Films panel"/>
<form method="post" action="films" enctype="multipart/form-data">
    <div class="container">
        <table>
            <tr>
                <input name="poster" placeholder="Upload poster image" type="file" accept="image/*"
                       required/></td>
            </tr>
            <tr>
                <td><input name="title" placeholder="Enter title" type="text" required/></td>

            </tr>
            <tr>

                <td><input name="description" placeholder="Enter description" type="text" required/></td>
            </tr>
            <tr>
                <td><input name="yearOfRelease" placeholder="Enter age year of release" type="text"
                           required/>
            </tr>
            <tr>
                <td><input name="ageRestrictions" placeholder="Enter age restrictions" type="text"
                           required/></td>
            </tr>
            <tr>
                <td>
                    <button type="Submit" class="btn">Add film</button>
                </td>
            </tr>
        </table>
    </div>
</form>
<@ui.tail/>