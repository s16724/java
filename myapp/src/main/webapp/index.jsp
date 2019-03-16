<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>kredyt</title>
</head>
<body>
<form action="index" method="post">
    <h3>Kalkulator kredytowy:</h3>
    <table>
        Wnioskowana kwota kredytu:<br/>
        <input type="number" min="1" name="kwotaKredytu"><br/>
        Ilosc rat :<br/>
        <input type="number" min="6" max="36" name="iloscRat">(minimum 6 maksimum 36)<br/>
        Oprocentowanie kredytu: <br/>
        <input type="number" min="0" name="oprocentowanie" ><br/>
        Oplata stala:<br/>
        <input name="oplataStala" type="number" ><br/>
        Rodzaj rat:<br/>
        <select name="rodzajRat">
            <option value="stala_wysokosc_rat">stala wysokosc rat</option>
            <option value="raty_malejace">raty malejace</option>
    </table>
    <br><input type="submit" value="Oblicz" name="oblicz">
</form>
</body>
</html>
