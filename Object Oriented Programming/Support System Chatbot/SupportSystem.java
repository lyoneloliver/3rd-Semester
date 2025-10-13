class SupportSystem
{
    private InputReader reader;
    private Responder responder;

    public SupportSystem()
    {
        reader = new InputReader();
        responder = new Responder();
    }

    public void start()
    {
        boolean finished = false;
        printWelcome();

        while (!finished) {
            String input = reader.getInput();
            if (input.toLowerCase().startsWith("bye")) {
                finished = true;
            } else {
                String response = responder.generateResponse(input);
                System.out.println(response);
            }
        }

        printGoodbye();
    }

    private void printWelcome()
    {
        System.out.println("Welcome to the DodgySoft Technical Support System.");
        System.out.println("Please tell us about your problem.");
        System.out.println("Type 'bye' to exit our system.");
    }

    private void printGoodbye()
    {
        System.out.println("Nice talking to you. Bye...");
    }
}