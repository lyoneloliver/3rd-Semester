class Responder
{
    private Random random;
    private String[] answers;
    private HashMap<String, Integer> keywords;
    private int allAnswers = 21;
    private int lastAnswer = -1;

    public Responder()
    {
        initAnswers();
        initKeywordList();
        random = new Random();
    }

    public String generateResponse(String input)
    {
        int ai = 0;
        boolean match = false;
        String[] splitInput = input.toLowerCase().split("[^a-zA-Z]");

        for (int i = 0; i < splitInput.length && !match; i++) {
            if (keywords.containsKey(splitInput[i])) {
                match = true;
                ai = keywords.get(splitInput[i]);
            }
        }

        if (!match) {
            do {
                ai = random.nextInt(10);
            } while (ai == lastAnswer);
        }

        lastAnswer = ai;
        return answers[ai];
    }

    private void initAnswers()
    {
        answers = new String[allAnswers];
        answers[0] = "Did you ask google?";
        answers[1] = "Did you turn it off and on?";
        answers[2] = "Keep talking, I'll get myself a coffee.";
        answers[3] = "Oh my gosh...";
        answers[4] = "42.";
        answers[5] = "I have no clue.";
        answers[6] = "Have a break, have a KitKat.";
        answers[7] = "Wenn's mal wieder länger dauert...";
        answers[8] = "Loading 31%.";
        answers[9] = "Talk to the hand.";
        answers[10] = "Java, Java, Java.";
        answers[11] = "Computer, Rechner, Ordenador.";
        answers[12] = "Apple, apple, apple...";
        answers[13] = "Microsoft, Winzigweich, pico fofo.";
        answers[14] = "The answer to life, the universe, and everything is 42.";
        answers[15] = "Mac, mac, mack, muck, mick...";
        answers[16] = "Aaahhh... we're all gonna die...";
        answers[17] = "This might take a little while.";
        answers[18] = "Printing is not good for the environment.";
        answers[19] = "Which distribution?";
        answers[20] = "Did you try rooting it?";
    }

    private void initKeywordList()
    {
        keywords = new HashMap<>();
        keywords.put("java", 10);
        keywords.put("computer", 11);
        keywords.put("ios", 12);
        keywords.put("windows", 13);
        keywords.put("life", 14);
        keywords.put("mac", 15);
        keywords.put("crashed", 16);
        keywords.put("loading", 17);
        keywords.put("print", 18);
        keywords.put("linux", 19);
        keywords.put("android", 20);
        keywords.put("problem", 2);
    }
}