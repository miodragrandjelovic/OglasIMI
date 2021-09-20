function predjiRadnik(){
    var registracijaPoslodavac = document.getElementById("registracijaPoslodavac");
    var registracijaRadnik = document.getElementById("registracijaRadnik");
    var radnikDugme = document.getElementById("radnikDugme");
    var poslodavacDugme = document.getElementById("poslodavacDugme");

    registracijaPoslodavac.style.display = "none";
    registracijaRadnik.style.display = "block";

    radnikDugme.disabled = true;
    radnikDugme.style.background = "#0040AA";
    poslodavacDugme.disabled = false;
    poslodavacDugme.style.background = "#0062CC";
}
function predjiPoslodavac(){
    var registracijaPoslodavac = document.getElementById("registracijaPoslodavac");
    var registracijaRadnik = document.getElementById("registracijaRadnik");
    var radnikDugme = document.getElementById("radnikDugme");
        var poslodavacDugme = document.getElementById("poslodavacDugme");

    registracijaPoslodavac.style.display = "block";
    registracijaRadnik.style.display = "none";

    radnikDugme.disabled = false;
    radnikDugme.style.background = "#0062CC";
    poslodavacDugme.disabled = true;
    poslodavacDugme.style.background = "#0040AA";
}
