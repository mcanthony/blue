<?xml version="1.0" encoding="ASCII"?>
<!--This file was created automatically by html2xhtml-->
<!--from the HTML stylesheets.-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" version="1.0">

<!-- ********************************************************************
     $Id: formal.xsl,v 1.2 2008/10/04 01:59:38 kunstmusik Exp $
     ********************************************************************

     This file is part of the XSL DocBook Stylesheet distribution.
     See ../README or http://docbook.sf.net/release/xsl/current/ for
     copyright and other information.

     ******************************************************************** -->

<xsl:param name="formal.object.break.after">1</xsl:param>

<xsl:template name="formal.object">
  <xsl:param name="placement" select="'before'"/>
  <xsl:param name="class">
    <xsl:apply-templates select="." mode="class.value"/>
  </xsl:param>

  <xsl:call-template name="id.warning"/>

  <xsl:variable name="content">
    <div class="{$class}">
      <xsl:call-template name="anchor">
        <xsl:with-param name="conditional" select="0"/>
      </xsl:call-template>
    
      <xsl:choose>
        <xsl:when test="$placement = 'before'">
          <xsl:call-template name="formal.object.heading"/>
          <div class="{$class}-contents">
            <xsl:apply-templates/>
          </div>
          <!-- HACK: This doesn't belong inside formal.object; it 
               should be done by the table template, but I want 
               the link to be inside the DIV, so... -->
          <xsl:if test="local-name(.) = 'table'">
            <xsl:call-template name="table.longdesc"/>
          </xsl:if>
    
          <xsl:if test="$spacing.paras != 0"><p/></xsl:if>
        </xsl:when>
        <xsl:otherwise>
          <xsl:if test="$spacing.paras != 0"><p/></xsl:if>
          <div class="{$class}-contents"><xsl:apply-templates/></div>
          <!-- HACK: This doesn't belong inside formal.object; it 
               should be done by the table template, but I want 
               the link to be inside the DIV, so... -->
          <xsl:if test="local-name(.) = 'table'">
            <xsl:call-template name="table.longdesc"/>
          </xsl:if>
    
          <xsl:call-template name="formal.object.heading"/>
        </xsl:otherwise>
      </xsl:choose>
    </div>
    <xsl:if test="not($formal.object.break.after = '0')">
      <br class="{$class}-break"/>
    </xsl:if>
  </xsl:variable>

  <xsl:variable name="floatstyle">
    <xsl:call-template name="floatstyle"/>
  </xsl:variable>

  <xsl:choose>
    <xsl:when test="$floatstyle != ''">
      <xsl:call-template name="floater">
        <xsl:with-param name="class"><xsl:value-of select="$class"/>-float</xsl:with-param>
        <xsl:with-param name="floatstyle" select="$floatstyle"/>
        <xsl:with-param name="content" select="$content"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:copy-of select="$content"/>
    </xsl:otherwise>
  </xsl:choose>

</xsl:template>

<xsl:template name="formal.object.heading">
  <xsl:param name="object" select="."/>
  <xsl:param name="title">
    <xsl:apply-templates select="$object" mode="object.title.markup">
      <xsl:with-param name="allow-anchors" select="1"/>
    </xsl:apply-templates>
  </xsl:param>

  <p class="title">
    <b>
      <xsl:copy-of select="$title"/>
    </b>
  </p>
</xsl:template>

<xsl:template name="informal.object">
  <xsl:param name="class" select="local-name(.)"/>

  <xsl:variable name="content">
    <div class="{$class}">
      <xsl:if test="$spacing.paras != 0"><p/></xsl:if>
      <xsl:call-template name="anchor"/>
      <xsl:apply-templates/>
  
      <!-- HACK: This doesn't belong inside formal.object; it 
           should be done by the table template, but I want 
           the link to be inside the DIV, so... -->
      <xsl:if test="local-name(.) = 'informaltable'">
        <xsl:call-template name="table.longdesc"/>
      </xsl:if>
  
      <xsl:if test="$spacing.paras != 0"><p/></xsl:if>
    </div>
  </xsl:variable>

  <xsl:variable name="floatstyle">
    <xsl:call-template name="floatstyle"/>
  </xsl:variable>

  <xsl:choose>
    <xsl:when test="$floatstyle != ''">
      <xsl:call-template name="floater">
        <xsl:with-param name="class"><xsl:value-of select="$class"/>-float</xsl:with-param>
        <xsl:with-param name="floatstyle" select="$floatstyle"/>
        <xsl:with-param name="content" select="$content"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:copy-of select="$content"/>
    </xsl:otherwise>
  </xsl:choose>

</xsl:template>

<xsl:template name="semiformal.object">
  <xsl:param name="placement" select="'before'"/>
  <xsl:param name="class" select="local-name(.)"/>

  <xsl:choose>
    <xsl:when test="title">
      <xsl:call-template name="formal.object">
        <xsl:with-param name="placement" select="$placement"/>
        <xsl:with-param name="class" select="$class"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:call-template name="informal.object">
        <xsl:with-param name="class" select="$class"/>
      </xsl:call-template>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template match="figure">
  <xsl:variable name="param.placement" select="substring-after(normalize-space($formal.title.placement),                                         concat(local-name(.), ' '))"/>

  <xsl:variable name="placement">
    <xsl:choose>
      <xsl:when test="contains($param.placement, ' ')">
        <xsl:value-of select="substring-before($param.placement, ' ')"/>
      </xsl:when>
      <xsl:when test="$param.placement = ''">before</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$param.placement"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <xsl:call-template name="formal.object">
    <xsl:with-param name="placement" select="$placement"/>
  </xsl:call-template>

</xsl:template>

<xsl:template match="table">
  <xsl:choose>
    <xsl:when test="tgroup|mediaobject|graphic">
      <xsl:call-template name="calsTable"/>
    </xsl:when>
    <xsl:otherwise>
      <!-- do not use xsl:copy because of XHTML's needs -->
      <table>
        <xsl:copy-of select="@*[not(local-name()='id')]"/>
        <xsl:attribute name="id">
          <xsl:call-template name="object.id"/>
        </xsl:attribute>
        <xsl:call-template name="htmlTable"/>
      </table>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template name="calsTable">
  <xsl:if test="tgroup/tbody/tr                 |tgroup/thead/tr                 |tgroup/tfoot/tr">
    <xsl:message terminate="yes">Broken table: tr descendent of CALS Table.</xsl:message>
  </xsl:if>

  <xsl:variable name="param.placement" select="substring-after(normalize-space($formal.title.placement),                                         concat(local-name(.), ' '))"/>

  <xsl:variable name="placement">
    <xsl:choose>
      <xsl:when test="contains($param.placement, ' ')">
        <xsl:value-of select="substring-before($param.placement, ' ')"/>
      </xsl:when>
      <xsl:when test="$param.placement = ''">before</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$param.placement"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <xsl:call-template name="formal.object">
    <xsl:with-param name="placement" select="$placement"/>
    <xsl:with-param name="class">
      <xsl:choose>
        <xsl:when test="@tabstyle">
          <!-- hack, this will only ever occur on table, not example -->
          <xsl:value-of select="@tabstyle"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:value-of select="local-name(.)"/>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:with-param>
  </xsl:call-template>
</xsl:template>

<xsl:template name="htmlTable">
  <xsl:if test="tgroup/tbody/row                 |tgroup/thead/row                 |tgroup/tfoot/row">
    <xsl:message terminate="yes">Broken table: row descendent of HTML table.</xsl:message>
  </xsl:if>

  <xsl:apply-templates mode="htmlTable"/>
</xsl:template>

<xsl:template match="example">
  <xsl:variable name="param.placement" select="substring-after(normalize-space($formal.title.placement),                      concat(local-name(.), ' '))"/>

  <xsl:variable name="placement">
    <xsl:choose>
      <xsl:when test="contains($param.placement, ' ')">
        <xsl:value-of select="substring-before($param.placement, ' ')"/>
      </xsl:when>
      <xsl:when test="$param.placement = ''">before</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$param.placement"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <xsl:call-template name="formal.object">
    <xsl:with-param name="placement" select="$placement"/>
  </xsl:call-template>

</xsl:template>

<xsl:template match="equation">
  <xsl:variable name="param.placement" select="substring-after(normalize-space($formal.title.placement),                                       concat(local-name(.), ' '))"/>

  <xsl:variable name="placement">
    <xsl:choose>
      <xsl:when test="contains($param.placement, ' ')">
        <xsl:value-of select="substring-before($param.placement, ' ')"/>
      </xsl:when>
      <xsl:when test="$param.placement = ''">before</xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="$param.placement"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:variable>

  <xsl:call-template name="formal.object">
    <xsl:with-param name="placement" select="$placement"/>
  </xsl:call-template>

</xsl:template>

<xsl:template match="figure/title"/>
<xsl:template match="figure/titleabbrev"/>
<xsl:template match="table/title"/>
<xsl:template match="table/titleabbrev"/>
<xsl:template match="table/textobject"/>
<xsl:template match="example/title"/>
<xsl:template match="example/titleabbrev"/>
<xsl:template match="equation/title"/>
<xsl:template match="equation/titleabbrev"/>

<xsl:template match="informalfigure">
  <xsl:call-template name="informal.object"/>
</xsl:template>

<xsl:template match="informalexample">
  <xsl:call-template name="informal.object"/>
</xsl:template>

<xsl:template match="informaltable">
  <xsl:choose>
    <xsl:when test="tgroup|mediaobject|graphic">
      <xsl:call-template name="informal.object">
        <xsl:with-param name="class">
          <xsl:choose>
            <xsl:when test="@tabstyle">
              <xsl:value-of select="@tabstyle"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="local-name(.)"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:with-param>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <table>
        <xsl:copy-of select="@*"/>
        <xsl:call-template name="htmlTable"/>
      </table>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template match="informaltable/textobject"/>

<xsl:template name="table.longdesc">
  <!-- HACK: This doesn't belong inside formal.objectt; it should be done by -->
  <!-- the table template, but I want the link to be inside the DIV, so... -->
  <xsl:variable name="longdesc.uri">
    <xsl:call-template name="longdesc.uri">
      <xsl:with-param name="mediaobject" select="."/>
    </xsl:call-template>
  </xsl:variable>

  <xsl:variable name="irrelevant">
    <!-- write.longdesc returns the filename ... -->
    <xsl:call-template name="write.longdesc">
      <xsl:with-param name="mediaobject" select="."/>
    </xsl:call-template>
  </xsl:variable>

  <xsl:if test="$html.longdesc != 0 and $html.longdesc.link != 0                 and textobject[not(phrase)]">
    <xsl:call-template name="longdesc.link">
      <xsl:with-param name="longdesc.uri" select="$longdesc.uri"/>
    </xsl:call-template>
  </xsl:if>
</xsl:template>

<xsl:template match="informalequation">
  <xsl:call-template name="informal.object"/>
</xsl:template>

<xsl:template name="floatstyle">
  <xsl:if test="(@float and @float != '0') or @floatstyle != ''">
    <xsl:choose>
      <xsl:when test="@floatstyle != ''">
        <xsl:value-of select="@floatstyle"/>
      </xsl:when>
      <xsl:when test="@float = '1'">
        <xsl:value-of select="$default.float.class"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="@float"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:if>
</xsl:template>

<xsl:template name="floater">
  <xsl:param name="content"/>
  <xsl:param name="class" select="'float'"/>
  <xsl:param name="floatstyle" select="'left'"/>

  <div class="{$class}">
    <xsl:if test="$floatstyle = 'left' or $floatstyle = 'right'">
      <xsl:attribute name="style">
        <xsl:text>float: </xsl:text>
        <xsl:value-of select="$floatstyle"/>
        <xsl:text>;</xsl:text>
      </xsl:attribute>
    </xsl:if>
    <xsl:copy-of select="$content"/>
  </div>
</xsl:template>

</xsl:stylesheet>
