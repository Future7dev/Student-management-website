let btn=document.querySelector(".logbtn");
let btn2=document.querySelector(".signbtn");

btn.addEventListener("click",function(){
    let crd1=document.querySelector(".options");
    crd1.classList.add("effectlog");

    let crd2=document.querySelector(".crd2");
    crd2.classList.add("effectent");
});
btn2.addEventListener("click",function(){
    let crd1=document.querySelector(".options");
    crd1.classList.add("effectlog");

    let crd3=document.querySelector(".crd3");
    crd3.classList.add("effectent");
});