# DashBoardView 基本表盘

**主要包含知识点：**
- 1.PathDashPathEffect 的使用。
- 2.PathMeasure 的使用
- 3.三角函数的一部分知识

## 思路
- 1.画一个弧线，canvas.drawArc()就ok
- 2.画上刻度，画刻度，需要知道弧线总长PathMearsure.getlenth()
- 3.使用paint.setPathEffect()来画间隙。
- 4.PathDashPathEffect(Path shape, float advance, float phase,Style style)
来添加具体的刻度的形状，间隙等
- 5.drawLine画指针

## 画弧线
