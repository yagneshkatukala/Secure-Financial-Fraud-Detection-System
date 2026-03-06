let transactions=[];

function login(){

let user=document.getElementById("username").value;
let pass=document.getElementById("password").value;

if(user=="" || pass==""){
document.getElementById("loginError").style.display="block";
}else{
document.getElementById("loginPage").style.display="none";
document.getElementById("mainSystem").style.display="block";
}

}

function showSection(id){

document.querySelectorAll(".content section").forEach(s=>{
s.classList.add("hidden");
});

document.getElementById(id).classList.remove("hidden");

}

function calculateRisk(){

let acc=document.getElementById("account").value;
let amt=+document.getElementById("amount").value;
let freq=+document.getElementById("frequency").value;
let card=document.getElementById("card").value;

let a=amt>50000?40:amt>20000?25:10;
let f=freq>5?25:10;

let total=a+f;

let status="Safe";

if(total>=60) status="Suspicious";
if(amt>100000) status="Fraud";

let percent=Math.min(total,100);

document.getElementById("riskPercent").innerText=percent+"%";
document.getElementById("riskBadge").innerText=status;

document.getElementById("showAccount").innerText=acc;
document.getElementById("detectedCard").innerText=card;

document.getElementById("amountRisk").innerText=a+"%";
document.getElementById("freqRisk").innerText=f+"%";

transactions.push({acc,amt,status});

updateReports();

showSection("riskDashboard");

}

function updateReports(){

let table=document.getElementById("reportTable");
table.innerHTML="";

let fraud=0;
let safe=0;

transactions.forEach(t=>{

if(t.status==="Fraud") fraud++;
else safe++;

table.innerHTML+=`
<tr>
<td>${t.acc}</td>
<td>${t.amt}</td>
<td>${t.status}</td>
</tr>
`;

});

document.getElementById("totalTx").innerText=transactions.length;
document.getElementById("fraudTx").innerText=fraud;
document.getElementById("safeTx").innerText=safe;

}