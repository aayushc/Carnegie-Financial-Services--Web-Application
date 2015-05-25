<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="emp_header.jsp"></jsp:include>
<jsp:include page="message.jsp"></jsp:include>

	<h2> Register New Customer </h2>
	
<hr>
<p class="help-block">
Create new customer accounts here. An email will be sent to let you set up password.</p>
<hr>
	<form role="form" action="registerCustomer.do" method="POST">
		<div class="form-group">
			<label for="userName">User Name</label>
			<input type="text" class="form-control" id="userName" name="userName" value="${form.userName}" placeholder="Customer User Name">
		</div>
		<div class="form-group">
			<label for="firstName">First Name</label>
			<input type="text" class="form-control" id="firstName" name="firstName" value="${form.firstName}" placeholder="Customer First Name">
		</div>
		<div class="form-group">
			<label for="lastName">Last Name</label>
			<input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName}" placeholder="Customer Last Name">
		</div>
		<div class="form-group">
			<label for="email">Email</label>
			<input type="email" class="form-control" id="email" name="email" value="${form.email}" placeholder="email&commat;email.com">
		</div>
		<div class="form-group">
			<label for="addrLine1">Address Line 1</label>
			<input type="text" class="form-control" id="addrLine1" name="addrLine1" value="${form.addrLine1}" placeholder="Street Address">
		</div>
		<div class="form-group">
			<label for="addrLine2">Address Line 2</label>
			<input type="text" class="form-control" id="addrLine2" name="addrLine2" value="${form.addrLine2}" placeholder="Additional Address">
		</div>
		<div class="form-group">
			<label for="city">City</label>
			<input type="text" class="form-control" id="city" name="city" value="${form.city}" placeholder="City">
		</div>
		<div class="form-group">
			<label for="state">State</label><br />
			<select class="form-control" id="state" name="state" onchange="selectOpt(this.options[this.selectedIndex].value)">
				<option id="opt1" value="AL" onclick="selectOpt(1)">AL</option>
				<option id="opt2" value="AK" onclick="selectOpt(2)">AK</option>
				<option id="opt3" value="AZ" onclick="selectOpt(3)">AZ</option>
				<option id="opt4" value="AR" onclick="selectOpt(4)">AR</option>
				<option id="opt5" value="CA" onclick="selectOpt(5)">CA</option>
				<option id="opt6" value="CO" onclick="selectOpt(6)">CO</option>
				<option id="opt7" value="CT" onclick="selectOpt(7)">CT</option>
				<option id="opt8" value="DE" onclick="selectOpt(8)">DE</option>
				<option id="opt9" value="FL" onclick="selectOpt(9)">FL</option>
				<option id="opt10" value="GA" onclick="selectOpt(10)">GA</option>
				<option id="opt11" value="HI" onclick="selectOpt(11)">HI</option>
				<option id="opt12" value="ID" onclick="selectOpt(12)">ID</option>
				<option id="opt13" value="IL" onclick="selectOpt(13)">IL</option>
				<option id="opt14" value="IN" onclick="selectOpt(14)">IN</option>
				<option id="opt15" value="IA" onclick="selectOpt(15)">IA</option>
				<option id="opt16" value="KS" onclick="selectOpt(16)">KS</option>
				<option id="opt17" value="KY" onclick="selectOpt(17)">KY</option>
				<option id="opt18" value="LA" onclick="selectOpt(18)">LA</option>
				<option id="opt19" value="ME" onclick="selectOpt(19)">ME</option>
				<option id="opt20" value="MD" onclick="selectOpt(20)">MD</option>
				<option id="opt21" value="MA" onclick="selectOpt(21)">MA</option>
				<option id="opt22" value="MI" onclick="selectOpt(22)">MI</option>
				<option id="opt23" value="MN" onclick="selectOpt(23)">MN</option>
				<option id="opt24" value="MS" onclick="selectOpt(24)">MS</option>
				<option id="opt25" value="MO" onclick="selectOpt(25)">MO</option>
				<option id="opt26" value="MT" onclick="selectOpt(26)">MT</option>
				<option id="opt27" value="NE" onclick="selectOpt(27)">NE</option>
				<option id="opt28" value="NV" onclick="selectOpt(28)">NV</option>
				<option id="opt29" value="NH" onclick="selectOpt(29)">NH</option>
				<option id="opt30" value="NJ" onclick="selectOpt(30)">NJ</option>
				<option id="opt31" value="NM" onclick="selectOpt(31)">NM</option>
				<option id="opt32" value="NY" onclick="selectOpt(32)">NY</option>
				<option id="opt33" value="NC" onclick="selectOpt(33)">NC</option>
				<option id="opt34" value="ND" onclick="selectOpt(34)">ND</option>
				<option id="opt35" value="OH" onclick="selectOpt(35)">OH</option>
				<option id="opt36" value="OK" onclick="selectOpt(36)">OK</option>
				<option id="opt37" value="OR" onclick="selectOpt(37)">OR</option>
				<option id="opt38" value="PA" onclick="selectOpt(38)">PA</option>
				<option id="opt39" value="RI" onclick="selectOpt(39)">RI</option>
				<option id="opt40" value="SC" onclick="selectOpt(40)">SC</option>
				<option id="opt41" value="SD" onclick="selectOpt(41)">SD</option>
				<option id="opt42" value="TN" onclick="selectOpt(42)">TN</option>
				<option id="opt43" value="TX" onclick="selectOpt(43)">TX</option>
				<option id="opt44" value="UT" onclick="selectOpt(44)">UT</option>
				<option id="opt45" value="VT" onclick="selectOpt(45)">VT</option>
				<option id="opt46" value="VA" onclick="selectOpt(46)">VA</option>
				<option id="opt47" value="WA" onclick="selectOpt(47)">WA</option>
				<option id="opt48" value="WV" onclick="selectOpt(48)">WV</option>
				<option id="opt49" value="WI" onclick="selectOpt(49)">WI</option>
				<option id="opt50" value="WY" onclick="selectOpt(50)">WY</option>
				<option id="opt51" value="AS" onclick="selectOpt(51)">AS</option>
				<option id="opt52" value="DC" onclick="selectOpt(52)">DC</option>
				<option id="opt53" value="FM" onclick="selectOpt(53)">FM</option>
				<option id="opt54" value="GU" onclick="selectOpt(54)">GU</option>
				<option id="opt55" value="MH" onclick="selectOpt(55)">MH</option>
				<option id="opt56" value="MP" onclick="selectOpt(56)">MP</option>
				<option id="opt57" value="PW" onclick="selectOpt(57)">PW</option>
				<option id="opt58" value="PR" onclick="selectOpt(58)">PR</option>
				<option id="opt59" value="VI" onclick="selectOpt(59)">VI</option>		
			</select>
		</div>
		<div class="form-group">
			<label for="zip">Zip Code</label>
			<input type="text" class="form-control" id="zip" name="zip" value="${form.zip}" placeholder="Postal Code">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" id="password" name="password" placeholder="Password">
		</div>
		<div class="form-group">
			<label for="password">Confirm Password</label>
			<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password">
		</div>
		<button type="submit" class="btn btn-primary" name="action" value="regCust">Register New Customer</button>
    </form>

<jsp:include page="footer.jsp"></jsp:include>