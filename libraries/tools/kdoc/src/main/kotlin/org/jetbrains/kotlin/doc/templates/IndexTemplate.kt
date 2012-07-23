package org.jetbrains.kotlin.doc.templates

import org.jetbrains.kotlin.doc.model.KModel

class IndexTemplate(val model: KModel) : KDocTemplate() {
  override fun render() {
    print("""<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<!--NewPage-->
<HTML>
<HEAD>
$generatedComment
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<TITLE>
${model.title}
</TITLE>
<SCRIPT type="text/javascript">
    targetPage = "" + window.location.search;
    if (targetPage != "" && targetPage != "undefined")
        targetPage = targetPage.substring(1);
    if (targetPage.indexOf(":") != -1)
        targetPage = "undefined";
    function loadFrames() {
        if (targetPage != "" && targetPage != "undefined")
             top.classFrame.location = top.targetPage;
    }
</SCRIPT>
<NOSCRIPT>
</NOSCRIPT>
</HEAD>
<FRAMESET cols="20%,80%" title="" onLoad="top.loadFrames()">
<FRAMESET rows="30%,70%" title="" onLoad="top.loadFrames()">
<FRAME src="overview-frame.html" name="packageListFrame" title="All Packages">
<FRAME src="allclasses-frame.html" name="packageFrame" title="All classes and interfaces (except non-static nested types)">
</FRAMESET>
<FRAME src="overview-summary.html" name="classFrame" title="Package, class and interface descriptions" scrolling="yes">
<NOFRAMES>
<H2>
Frame Alert</H2>

<P>
This document is designed to be viewed using the frames feature. If you see this message, you are using a non-frame-capable web client.
<BR>
Link to<A HREF="overview-summary.html">Non-frame version.</A>
</NOFRAMES>
</FRAMESET>
</HTML>
""")
  }
}
