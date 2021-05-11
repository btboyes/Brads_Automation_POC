public class carsPage extends basePage {

    private static carsPageActController act = new carsPageActController();
    private static carsPageGetController get = new carsPageGetController();
    private static carsPageVerifyController verify = new carsPageVerifyController();

    public static carsPageActController act() {
        return act;
    }

    public static carsPageGetController get() {
        return get;
    }

    public static carsPageVerifyController verify() {
        return verify;
    }

    private carsPage() {
    }
}


