# AndroidCalculator

```mermaid
  graph 
    num --> id1_1((setInputNum))
    operator --> id1_2((setInputOperator))
    subgraph calculator
    id1_1 --> inputNum --> id2((getResult))
    id1_2 --> setOperator --> id2
    end
    id2 --> result
    id2 --> cf(Calcuation fomula)
```



## 숫자

> tag에 데이터를 저장해 받는다.

## 연산자

| 연산자 | 메소드이름            | 하는일    |
| ------ | --------------------- | --------- |
| %      | percentBtnClick       | 1/100 * x |
| CE     | allClearBtnClick      |           |
| C      | clearBtnClick         |           |
| delete | deleteLastNumBtnClick |           |
| 1/x    | reverseNumBtnClick    | 1/x       |
| x^2    | squareNumBtnClick     |           |
| x^-2   | squareRootNumBtnClick |           |
| ÷      | divide                |           |
| ＋     | plus                  |           |
| －     | minus                 |           |
| ×      | multiply              |           |
| ＝     | equal                 |           |
| ±      | multiplyMinus         |           |


