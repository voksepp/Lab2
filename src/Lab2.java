import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab2 {

    /**
     * Performs trades. Sells each share where the lowest sell price
     * is lower than that of the buyer bid. The share is traded at the
     * price specified by the seller. Prints each trade.
     * <p>
     * After trades have been completed, the remaining bids are printed.
     *
     * @param bids List of bids
     */
    private static void trade(List<Bid> bids) {
        BuyComparator buyComparator = new BuyComparator();
        SellComparator sellComparator = new SellComparator();
        PriorityQueue<Bid> buyPriorityQueue = new PriorityQueue<>(buyComparator);
        PriorityQueue<Bid> sellPriorityQueue = new PriorityQueue<>(sellComparator);
        Map<String, Bid> bidMap = new HashMap<>();

        for (Bid b : bids) {
            switch (b.getType()) {
                case "K":
                    bidMap.put(b.getName(), b);
                    buyPriorityQueue.insert(b);
                    break;
                case "S":
                    bidMap.put(b.getName(), b);
                    sellPriorityQueue.insert(b);
                    break;
                case "NK":
                    if (bidMap.containsKey(b.getName())) {
                        Bid a = bidMap.get(b.getName());
                        a.updateValue(b.getValue(), b.getOldValue());
                        buyPriorityQueue.replace(bidMap.get(b.getName()), a);
                    } else {
                        System.out.println("Ogiltig ändring av bud, felaktigt namn");
                    }
                    break;
                case "NS":
                    if (bidMap.containsKey(b.getName())) {
                        Bid a = bidMap.get(b.getName());
                        a.updateValue(b.getValue(), b.getOldValue());
                        sellPriorityQueue.replace(bidMap.get(b.getName()), a);
                    } else
                        System.out.println("Ogiltig ändring av bud, felaktigt namn");
                    break;
            }

            if (buyPriorityQueue.size() != 0 && sellPriorityQueue.size() != 0) {
                if (buyPriorityQueue.peek().getValue() >= sellPriorityQueue.peek().getValue()) {
                    Bid buy = buyPriorityQueue.removeFirst();
                    Bid sell = sellPriorityQueue.removeFirst();

                    System.out.println(buy.getName() + " köper från " + sell.getName() + " för " + sell.getValue() + " kr");
                }
            }
        }

        printOrderBook(buyPriorityQueue, sellPriorityQueue);
    }

    /**
     * Prints the order book of remaining bids
     *
     * @param buyQueue  Priority queue of buyer bids
     * @param sellQueue Priority queue of seller bids
     */
    private static void printOrderBook(PriorityQueue buyQueue, PriorityQueue sellQueue) {
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

    private static Bid parseBid(String s) throws MalformedBid {
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
                return new Bid(m.group(1), m.group(2), Integer.parseInt(m.group(3)));  // Incomplete code. ...
            }
        } else {
            throw new MalformedBid(s);
        }
    }

    /**
     * Parses line-separated bids from the given Readable thing.
     *
     * @param input The character stream that should be parsed.
     * @throws MalformedBid If some bid couldn't be parsed.
     */

    private static List<Bid> parseBids(Readable input) throws MalformedBid {
        ArrayList<Bid> bids = new ArrayList<>();
        Scanner s = new Scanner(input);

        while (s.hasNextLine()) {
            bids.add(parseBid(s.nextLine()));
        }

        return bids;
    }

    /**
     * Prints usage info.
     */

    private static void usageInfo() {
        System.err.println("Usage: java Aktiehandel [<file>]");
        System.err.println("If no file is given, then input is " +
                "read from standard input.");
    }

    /**
     * Reads a list of bids, makes sure they are correctly formatted and then calls the trade method
     *
     * @param args file name
     * @throws FileNotFoundException throws an exception if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length >= 2) {
            usageInfo();
        } else {
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

    /**
     * Exception class for malformed bids.
     */

    private static class MalformedBid extends Exception {
        MalformedBid(String bid) {
            super("Malformed bid: " + bid + ".");
        }
    }
}