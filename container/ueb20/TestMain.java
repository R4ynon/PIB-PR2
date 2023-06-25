package ueb20;

import java.util.*;

public class TestMain {

    private LinkedList<Integer> integerLinked;
    private PriorityQueue<Integer> priorityQueue;
    private static final String EMPTY_LIST = "List is empty!";


    public static void main(String[] args) {
        TestMain main = new TestMain();


        //main.startOffLoop(1);
        //main.inputDecision();
    }

    public void inputDecision(){
        int decision = 0;
        //hier wir die entscheidung getroffen ob fifo oder natürliche sortierung
        startOffLoop(decision);
    }

    public void startOffLoop(int decision){
        Consumer con = new Consumer();

        integerLinked = new LinkedList<Integer>();
        priorityQueue = new PriorityQueue<Integer>();
        Random ran = new Random();
        int checksumm = 0;

        for(int i = 0;i<10000;i++) {
            if (ran.nextInt(2) > 0) {
                if(decision == 0){
                    integerLinked.addFirst(Producer.produce());
                }
                else{
                    priorityQueue.add(Producer.produce());
                }
            }
            else {
                if(decision == 0){
                    if(integerLinked.isEmpty() == true) {
                        throw new IllegalArgumentException(EMPTY_LIST);
                    }else{
                        checksumm = con.consume(integerLinked.pollLast());

                    }

                }
                else{
                    if(priorityQueue.isEmpty() == true) {
                        throw new IllegalArgumentException(EMPTY_LIST);
                    }else{
                        checksumm = con.consume(priorityQueue.poll());
                    }
                }
                con.insertIntoChecksummMap(checksumm);



            }
        }
    }

}
