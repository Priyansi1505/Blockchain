const API = "http://localhost:8080/api";

async function createTx() {
    let from = document.getElementById('from').value;
    let to = document.getElementById('to').value;
    let amount = document.getElementById('amount').value;

    let res = await fetch(`${API}/transaction?from=${from}&to=${to}&amount=${amount}`, {
        method: "POST"
    });
    alert(await res.text());
    refresh();
}

async function mine() {
    let res = await fetch(`${API}/mine`, { method: "POST" });
    alert(await res.text());
    refresh();
}

async function refresh() {
    document.getElementById("chainOutput").innerText =
        JSON.stringify(await (await fetch(`${API}/chain`)).json(), null, 2);

    document.getElementById("mempoolOutput").innerText =
        JSON.stringify(await (await fetch(`${API}/mempool`)).json(), null, 2);

    document.getElementById("utxoOutput").innerText =
        JSON.stringify(await (await fetch(`${API}/utxo`)).json(), null, 2);
}

refresh();
