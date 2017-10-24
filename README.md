<h1>Json processor<h1>

<span>It's just nothing, but my ideas, how to solve the following task:<span>

<ul>
<li> As an input you have multiply files in json format </li>
<li> Every file has at least billion records</li>
<li> Every record follow an abstract interface { recordId, Name,DateCreated, DueDate, DateUpdated, AudioLength, Content}</li>
<li> Task is to find all records without filled 'Name' and 'AudioLength' and print number of records without filled fields against every field</li>
</ul>