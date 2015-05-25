<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="form-group">
      <select class="form-control" id="lookUpOpt" name="opt">
		<option value="name">Search by name: </option>
		<option value="symbol">Search by symbol: </option>
	</select>
    </div>
	<div class="form-group">
		<label class="sr-only" for="lookUpKey">Keyword</label>
		<input class="form-control" type="text" id="lookUpKey" name="value" placeholder="Search Keyword" onkeyup="searchSuggest(this);" 
		 onblur="lostFocus(this);" autocomplete="off" value="" />
		 <div style="position: absolute;background-color:white;text-align:left;z-index: 100" id="search_suggest"></div>