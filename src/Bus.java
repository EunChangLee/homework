class Bus extends PublicTransport {
    static int _number = 0;
    int _myNumber = 0;
    int getNumber(){
        return _myNumber;
    }
    void setNumber(int number){
        _myNumber = number;
    }
    int _totalFare = 0;
    int _fare = 1000; //요금
    public State _state;
    public enum State{
        운행,
        차고지행
    }
    Bus() {
        _maxSonNim = 30;
        _number += 1;
        _fuelVolume = 100;
        setNumber(_number);
        startService();
    }
    //운행 시작
    public void startService(){
        if(_fuelVolume >= 10){
            System.out.println(_myNumber + "번 버스 운행을 시작합니다.");
            _state = State.운행;
            return;
        }

        System.out.println("연료가 부족합니다. 운행할 수 없습니다.");
        _state = State.차고지행;
    }
    //운행 종료
    void endService() {
        System.out.println(_myNumber + "번 버스 운행을 종료합니다.");
        _state = State.차고지행;
    }
    //속도 변경
    public void speedChange(int speed){
        if(_state != State.운행){
            System.out.println("운행중이 아닙니다.");
            return;
        }
        checkFuelVolume();

        _speed += speed;

        if(_speed < 0)
            _speed = 0;

        if(_speed > 150)
            _speed = 150;

        System.out.println("현재 속력 : " + _speed + "km 입니다.");
    }
    //상태 변경
    public void stateChange(State state) {
        _state = state;
    }
    //연료 소모
    public void usedFuel(int fuel) {
        if(_fuelVolume < fuel){
            System.out.println("연료가 부족합니다.");
            return;
        }

        _fuelVolume -= fuel;
        System.out.println("연료가 " + fuel + " 소모되었습니다.");
        checkFuelVolume();
    }
    //연료 충전
    public void reFuel(int fuel){
        if(_fuelVolume >= 100)
        {
            System.out.println("이미 연료가 가득 찬 상태입니다.");
            return;
        }

        _fuelVolume += fuel;
        if(_fuelVolume > 100)
            _fuelVolume = 100;

        System.out.println("상태 : " + _state);
        System.out.println("현재 남은 연료량 : " + _fuelVolume);
    }
    //연료가 얼마나 있는지 체크
    public void checkFuelVolume(){
        System.out.println("현재 연료량 " + _fuelVolume);

        if(_fuelVolume < 10){

            System.out.println("주유가 필요합니다.");
            System.out.println("차고지로 돌아갑니다.");
            _state = State.차고지행;
            return;
        }
    }
    //손님 태움
    public void getOnThe(int sonNim){
        if(_state != State.운행)
            return;

        if(_nowSonNim + sonNim > _maxSonNim){
            System.out.println("최대 승객 수를 초과하였습니다.");
            System.out.println("남은 좌석 : " + (_maxSonNim - _nowSonNim));
            return;
        }
        System.out.println("탑승 승객 수 : " + sonNim);
        _nowSonNim += sonNim;
        _totalFare += _fare * sonNim;
        System.out.println("받은 요금 : " + (_fare * sonNim));
        System.out.println("남은 좌석 : " + (_maxSonNim - _nowSonNim));
    }
}