# Hönnunarskýrsla

### Inngangur
Áðuren að við byrjuðum að vinna í kerfinu sjálfu punktuðum við niður nokkur atriðið sem okkur þótti gott að fara yfir fyrir vinnslu á kerfinu. Við skrifuðum kröfulista sem hélt utanum kröfur og flokkaði þær eftir vagi (A, B eða C) krafanna. Einnig rissuðum við upp einfalt klasarit þar sem að við reyndum okkar besta að brjóta forritið upp í eindir bæði til að auðvelda prófanir og gera okkur kleift að geta auðveldlega breytt og bætt kerfið okkar. Að lokum gerðum við einfalda frumgerð sem gaf okkur sýn á það hvernig vefsíðan ætti að lokum að líta út.

### Kröfulisti

###### (A) kröfur
  - Leikarinn þarf að geta sýnt leikborð
  - Leikurinn þarf að finna út hvenær honum lýkur og bregðast við því
  - Leikurinn þarf að halda utan um hver á að gera
  - Notandi þarf að geta fyllt í reit
  - Leikurinn þarf að halda utan um reitina og stöðu þeirra
  - Notandi má ekki geta fyllt í reit sem er þegar fylltur
  - Leifa 2 notendum að spila (2 player) - á sömu tölvu

###### (B) kröfur
  - Notandi geti hætt leik í miðjum leik
  - Að leik loknum þarf notandi að geta valið hvort hann vilji spila annan leik
  - Leifa einum notanda að spila móti tölvu (1 player)
  - Leikmaður getur sett inn hvaða tákn hann vill spila sem
  - Leikurinn stöðvar leikinn þegar enginn getur unnið
  - Leikmaður getur sett inn nafn

###### \(C\) kröfur
  - Hægt er að halda utan um fleiri en 1 leik með session
  - Leikurinn heldur utan um stigatöflu
  - Notandi geti valið stærð á leikborði frá 3x3 upp í 10x10
  - Notandi sem spilar á móti tölvu getur valið erfiðleikasstig tölvunnar
  - Getir simulate'að leik

# Klasarit

![class_diagram.png](https://scontent-arn2-1.xx.fbcdn.net/v/t34.0-12/22635189_10154847870777665_1272580463_n.png?oh=d43765756f8e81e004e8c15a8b26c755&oe=59EFC4F1 "Logo Title Text 1")


# Frumgerð

Forsíða leiksins, þar er valið hvernig leikurinn á að vera og hægt að skoða stigatöfluna.

![start_page.png](https://i.imgur.com/b05kQvw.png "Logo Title Text 1")

Þegar leikur byrjar er hægt að velja hnappinn "End game" sem fer aftur á forsíðu. Það stendur alltaf hver á að gera.

![board_page.png](https://i.imgur.com/cdmLN76.png "Logo Title Text 1")

Þegar leikmaður hefur unnið lætur leikurinn vita af því. Þá er hægt að velja hnappinn "change settings" til að fara aftur á forsíðu, eða hnappinn "play again?" til að spila aftur með sömu stillingum.

![finished_game.png](https://i.imgur.com/BchSRs5.png "Logo Title Text 1")

Stigataflan. Af henni er hægt að fara til baka á forsíðu.

![finished_game.png](https://i.imgur.com/NKJq7CW.png "Logo Title Text 1")
