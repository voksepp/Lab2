import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * ...
 */

public class Lab2 {

    /**
     *
     * @param bids
     */

    public static void trade(List<Bid> bids) {
        BuyComparator buyComparator = new BuyComparator();
        SellComparator sellComparator = new SellComparator();
        PriorityQueue<Bid> buyPriorityQueue = new PriorityQueue(buyComparator);
        PriorityQueue<Bid> sellPriorityQueue = new PriorityQueue(sellComparator);

        Map<String, Bid> bidMap = new HashMap<>();

        for (Bid b: bids){
            if(b.getType().equals("K")) {
                bidMap.put(b.getName(),b);
                buyPriorityQueue.insert(b);
            }
            else if (b.getType().equals("S")) {
                bidMap.put(b.getName(),b);
                sellPriorityQueue.insert(b);
            }
            else if (b.getType().equals("NK")){
                if(bidMap.containsKey(b.getName())) {
                    Bid a = bidMap.get(b.getName());
                    a.updateValue(b.getValue(),b.getOldValue());
                    buyPriorityQueue.replace(bidMap.get(b.getName()),a);
                }
                else{
                    System.out.println("Ogiltig ändring av bud, felaktigt namn");
                }
            }
            else if (b.getType().equals("NS")){
                if(bidMap.containsKey(b.getName())) {
                    Bid a = bidMap.get(b.getName());
                    a.updateValue(b.getValue(),b.getOldValue());
                    sellPriorityQueue.replace(bidMap.get(b.getName()),a);
                }
                else
                    System.out.println("Ogiltig ändring av bud, felaktigt namn");
            }

            if(buyPriorityQueue.getSize() != 0 && sellPriorityQueue.getSize() != 0){
                if(buyPriorityQueue.peek().getValue() >= sellPriorityQueue.peek().getValue()){
                    Bid buy = buyPriorityQueue.pop();
                    Bid sell = sellPriorityQueue.pop();

                    System.out.println(buy.getName() + " köper från " + sell.getName() + " för " + sell.getValue() + " kr");
                }
            }
        }

        printOrderBook(buyPriorityQueue,sellPriorityQueue);
    }

    /**
     *
     * @param buyQueue
     * @param sellQueue
     */
    private static void printOrderBook (PriorityQueue buyQueue, PriorityQueue sellQueue){
        System.out.println("Orderbok:");
        System.out.println("Säljare: " + sellQueue.toString());
        System.out.println("Köpare: " + buyQueue.toString());
    }

    /**
     * Parses a bid.
     *
     * @param s The string that should be parsed.
     * @throws MalformedBid If the bid cannot be parsed.
     */

    public static Bid parseBid(String s) throws MalformedBid {
        Matcher m = Pattern.compile(
                "\\s*(\\S+)\\s+" +
                        "(?:(K|S)\\s+(\\d+)|(NS|NK)\\s+(\\d+)\\s+(\\d+))" +
                        "\\s*").matcher(s);

        if (m.matches()) {
            if (m.group(2) == null) {
                // m.group(1): The name of the buyer/seller.
                // m.group(4): NK or NS.
                // m.group(5): Old value.
                // m.group(6): New value.
                return new Bid(m.group(1), m.group(4), Integer.parseInt(m.group(6)), Integer.parseInt(m.group(5)));  // Incomplete code.
            } else {
                // m.group(1): The name of the buyer/seller.
                // m.group(2): K or S.
                // m.group(3): The value.
                return new Bid(m.group(1), m.group(2) ,Integer.parseInt(m.group(3)));  // Incomplete code. ...
            }
        } else {
            throw new MalformedBid(s);
        }
    }

    /**
     * Parses line-separated bids from the given Readable thing.
     *
     * @param input The character stream that should be parsed.
     *
     * @throws MalformedBid If some bid couldn't be parsed.
     */

    public static List<Bid> parseBids(Readable input) throws MalformedBid {
        ArrayList<Bid> bids = new ArrayList<Bid>();
        Scanner s = new Scanner(input);

        while (s.hasNextLine()) {
            bids.add(parseBid(s.nextLine()));
        }

        return bids;
    }

    /**
     * Exception class for malformed bids.
     */

    public static class MalformedBid extends Exception {
        MalformedBid(String bid) {
            super("Malformed bid: " + bid + ".");
        }
    }

    /**
     * Prints usage info.
     */

    public static void usageInfo() {
        System.err.println("Usage: java Aktiehandel [<file>]");
        System.err.println("If no file is given, then input is " +
                "read from standard input.");
    }

    /**
     * ...
     */

    public static void main(String[] args) throws FileNotFoundException{
        if (args.length >= 2) {
            usageInfo();
        }
        else {
            try {
                BufferedReader r;
                if (args.length == 0) {
                    // Read from stdin.
                    r = new BufferedReader(new InputStreamReader(System.in));
                } else {
                    // Read from a named file.
                    r = new BufferedReader(new FileReader(args[0]));
                }

                try {
                    List<Bid> bids = parseBids(r);
                    trade(bids);
                } finally {
                    r.close();
                }
            } catch (MalformedBid e) {
                System.err.println(e.getMessage());
                usageInfo();
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0] + ".");
                usageInfo();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                usageInfo();
            }
        }
    }
}