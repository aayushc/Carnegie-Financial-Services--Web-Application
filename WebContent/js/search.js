function searchSuggest(invoker) {
	if (request.readyState != 0) return;
	
	var last  = document.getElementById("last").value;
	
	if (last == "") {
		cleanSuggestedResults("search_suggest_last");
		return;
	}
	
	//We set the url for the request.
	var url = "search-ajax.do?" +
			  "last=" + escape(last);
			
	//We set a different callback function depending on who
	//was the invoker that launch the event.
	if (invoker.id == "last") {
		request.onreadystatechange = updateLastNameSearch;
	} 
	
	//We send the request
	request.open("GET",url,true);
	request.send(null);
}

function updateLastNameSearch() {
	suggestUpdate("search_suggest_last");
}

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
	
	for (var i=0; i<results.length && i<20; i++) {
		var id        = results[i].getElementsByTagName("id")[0].firstChild.nodeValue;
		var last      = results[i].getElementsByTagName("lastName")[0].firstChild.nodeValue;
		
		var suggestText   = last;
		var suggestURL    = "lookup-entry.do?id="+id;
		var suggestLineEl = createSuggestLine(suggestURL, suggestText);
		suggestDivEl.appendChild(suggestLineEl);
	}

	if (results.length > 20) {
		var last  = document.getElementById("last").value;
		var searchURL  = "search.do?last=" + escape(last);
		suggestDivEl.appendChild(createSuggestLine(searchURL,"..."));
	}

	request = createRequest();
}


function createSuggestLine(url,text) {
	var textEl = document.createTextNode(text);
	
	var divEl = document.createElement("div");
	divEl.appendChild(textEl);
	divEl.className   = "suggest_list_div";
	divEl.onmouseover = suggestOver;
	divEl.onmouseout  = suggestOut;

	var anchorEl  = document.createElement("a");
	anchorEl.href = url;
	anchorEl.className="suggest_href";
	anchorEl.appendChild(divEl);

	return anchorEl;
}

//This method cleans the suggested results.
//It receives as an argument the Id of the Div element that has the suggested results.
//and it erases all its contents.
function cleanSuggestedResults(elementId) {
	var divSuggestResults = document.getElementById(elementId);
	while (divSuggestResults.hasChildNodes()) {
		divSuggestResults.removeChild(divSuggestResults.firstChild);
	}
}

//This method is called when the user passes the mouse over the suggestion.
//It just change the color of the box and the style of mouse pointer.
function suggestOver() {
	document.body.style.cursor = 'pointer';
	this.className = "suggest_list_div_over";
}

//This method is called when the user passes the mouse out of the suggestion.
//It just change the color of the box and the style of mouse pointer.
function suggestOut() {
	document.body.style.cursor = 'default';
	this.className = "suggest_list_div";
}


//This method is called after the searchboxes had lost their focus. In this case, we must
//clear the contents of the suggestions.
//I use the Javascript setTimeout method for executing the clearSuggestedResults method 500ms in the
//future. I need to do this to prevent clearing the suggested results before they are copied to the
//search box.
function lostFocus(element) {
	document.getElementById("last").value = "";
	setTimeout("cleanup(\""+element.id+"\")",200);
}

function cleanup(elementId) {
	cleanSuggestedResults("search_suggest_last");
	
}
