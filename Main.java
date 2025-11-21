public class Network {
    private Chain chain = new Chain();
    private Node A = new Node("A", chain);
    private Node B = new Node("B", chain);
    private Node C = new Node("C", chain);
    private Miner miner = new Miner("Miner", A, 2);

    public Network() {
        chain.createGenesisUTXO("Alice", 100);
    }

    public String createTransaction(String from, String to, double amount) {
        Transaction tx = chain.createTransaction(from, to, amount);
        if (tx == null)
            return "Transaction rejected.";
        A.receiveTx(tx);
        B.receiveTx(tx);
        C.receiveTx(tx);
        return "Transaction broadcast: " + tx.toString();
    }

    public String mineBlock() {
        Block block = miner.mineOnce();
        if (block == null)
            return "No transactions to mine.";
        A.receiveBlock(block);
        B.receiveBlock(block);
        C.receiveBlock(block);
        return "Block mined: " + block.hash;
    }

    public Object getChain() { return chain.blocks; }
    public Object getMempool() { return A.getMempool(); }
    public Object getUtxo() { return chain.getUtxo(); }

    public void reset() {
        this.chain = new Chain();
        chain.createGenesisUTXO("Alice", 100);
    }
}
