<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
<html>
<body style="background-color:#f0f8ff;">

<h2 style="color:#4b0082; text-align:center;">
Hogwarts University Students
</h2>

<table border="1" align="center" cellpadding="5"
       style="border-collapse:collapse; background-color:#ffffff;">

<tr style="background-color:#2e2e2e; color:white;">
<th>Name</th>
<th>Roll Number</th>
<th>House</th>
</tr>

<xsl:for-each select="students/s">
<tr style="background-color:#e6e6fa;">
<td><xsl:value-of select="name"/></td>
<td><xsl:value-of select="rono"/></td>
<td><xsl:value-of select="branch"/></td>
</tr>
</xsl:for-each>

</table>

</body>
</html>
</xsl:template>

</xsl:stylesheet>
