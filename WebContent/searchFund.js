// This JSP adapted from Prof. Eppinger Address Book Example Courtesy of Proff. Jeff Eppinger
function searchSuggest(invoker) {
	if (request.readyState != 0) return;
	
	var lookUpOpt   = document.getElementById("lookUpOpt").value;
	var lookUpKey   = document.getElementById("lookUpKey").value;
	
	if (lookUpKey == "") {
		cleanSuggestedResults("search_suggest");
		return;
	}
	
	//We set the url for the request.
	var url = "searchFundAjax.do?" + "opt=" + escape(lookUpOpt) + "&value=" + escape(lookUpKey);
			
	//We set a different callback function depending on who
	//was the invoker that launch the event.
	request.onreadystatechange = updateSearch;
	
	//We send the request
	request.open("POST",url,true);
	request.send(null);
}

function updateSearch() {
	suggestUpdate("search_suggest");
}

/*function updateLastNameSearch() {
	suggestUpdate("search_suggest_last");
}

function updateFirstNameSearch() {
	suggestUpdate("search_suggest_first");
}*/

//In this method we handle the request that the last name inputbox has launched.
//Here we display the suggested results under the last name inputbox.
function suggestUpdate(elementId) {
	if (request.readyState != 4) return;
	
	if (request.status != 200) {
		alert("Error, request status is "+request.status);
		return;
	}
		
	cleanSuggestedResults(elementId);
	var xmlDoc  = request.responseXML;
	var results = xmlDoc.getElementsByTagName("result");	
		
	var suggestDivEl = document.getElementById(elementId);
	var forwardURL = document.getElementById("searchSource").action;
	
	for (var i=0; i<results.length && i<20; i++) {
		var id     = results[i].getElementsByTagName("fundId")[0].firstChild.nodeValue;
		var name   = results[i].getElementsByTagName("fundName")[0].firstChild.nodeValue;
		var symbol = results[i].getElementsByTagName("fundSymbol")[0].firstChild.nodeValue;
		
		var suggestText   = name+", "+symbol;
		var suggestURL    = forwardURL+"?searchId="+id;
		var suggestLineEl = createSuggestLine(suggestURL, suggestText);
		suggestDivEl.appendChild(suggestLineEl);
	}

	//if (results.length > 20) {
		//var last  = document.getElementById("last").value;
		//var first = document.getElementById("first").value;
		//var searchURL  = "search.do?last=" + escape(last) + "&first=" + escape(first);
		//suggestDivEl.appendChild(createSuggestLine(searchURL,"..."));
	//}

	request = createRequest();
}

function createSuggestLine(url,text) {
	var textEl = document.createTextNode(text);
	
	var divEl = document.createElement("div");
	divEl.appendChild(textEl);
	/*divEl.className   = "suggest_list_div";
	divEl.onmouseover = suggestOver;
	divEl.onmouseout  = suggestOut;*/

	var anchorEl  = document.createElement("a");
	anchorEl.href = url;
	/*anchorEl.className="suggest_href";*/
	anchorEl.appendChild(divEl);

	return anchorEl;
}

// This method cleans the suggested results.
// It receives as an argument the Id of the Div element that has the suggested results.
// and it erases all its contents.
function cleanSuggestedResults(elementId) {
	var divSuggestResults = document.getElementById(elementId);
	while (divSuggestResults.hasChildNodes()) {
		divSuggestResults.removeChild(divSuggestResults.firstChild);
	}
}

// This method is called when the user passes the mouse over the suggestion.
// It just change the color of the box and the style of mouse pointer.
/*function suggestOver() {
	document.body.style.cursor = 'pointer';
	this.className = "suggest_list_div_over";
}*/

// This method is called when the user passes the mouse out of the suggestion.
// It just change the color of the box and the style of mouse pointer.
/*function suggestOut() {
	document.body.style.cursor = 'default';
	this.className = "suggest_list_div";
}*/


// This method is called after the searchboxes had lost their focus. In this case, we must
// clear the contents of the suggestions.
// I use the Javascript setTimeout method for executing the clearSuggestedResults method 500ms in the
// future. I need to do this to prevent clearing the suggested results before they are copied to the
// search box.
function lostFocus(element) {
	document.getElementById("lookUpKey").value = "";
	setTimeout("cleanup(\""+element.id+"\")",200);
}

function cleanup(elementId) {
	if (elementId == "lookUpKey") {
		cleanSuggestedResults("search_suggest");
	} //else {
		//cleanSuggestedResults("search_suggest_first");
	//}
}
