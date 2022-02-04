

function popup(mylink, windowname)
{
	if (! window.focus)
		return true;


	var href;
	if (typeof(mylink) == 'string')
	   href=mylink;
	else
		{
		   href=mylink.href;
		   var IE6 = (navigator.userAgent.indexOf("MSIE 6")>=0) ? true : false;
			if(IE6) {
			   window.showModalDialog(href, windowname, 'dialogWidth:730px;dialogHeight:620px;dialogTop:190px;dialogLeft:190px;status:no;scroll:no;');
			}
			else{
				window.showModalDialog(href, windowname, 'dialogWidth:730px;dialogHeight:620px;dialogTop:190px;dialogLeft:190px;status:no;scroll:no;');
			}
		   return false;
	   }
}

function popupUser(mylink, windowname)
{
    if (! window.focus)
        return true;


    var href;
    if (typeof(mylink) == 'string')
       href=mylink;
    else
        {
           href=mylink.href;
           var IE6 = (navigator.userAgent.indexOf("MSIE 6")>=0) ? true : false;
            if(IE6) {
               window.showModalDialog(href, windowname, 'dialogWidth:730px;dialogHeight:350px;dialogTop:190px;dialogLeft:190px;status:no;scroll:no;');
            }
            else{
                window.showModalDialog(href, windowname, 'dialogWidth:730px;dialogHeight:350px;dialogTop:190px;dialogLeft:190px;status:no;scroll:no;');
            }
           return false;
       }
}

function getPara(name)
{
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\?&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( window.location.href );
	if( results == null )
		return "";
	else
		return results[1];
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}