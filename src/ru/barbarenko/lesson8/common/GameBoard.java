package ru.barbarenko.lesson8.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Игровая доска для игры в крестики-нолики
 * произвольного размера
 * @author Alex Dl.
 *
 */
public class GameBoard implements Serializable {

    private static final long serialVersionUID = 143534543543L;

    private int size;
    private BoardItem[][] board;
    private Collection<Integer[]> winItems;

    /**
     * Создать игровую доску размером size x size
     * @param size размер доски, не менее 3
     */
    public GameBoard(int size) {
        setSize(size);
        winItems = new ArrayList<Integer[]>();
    }

    /**
     *
     * @return размер игровой доски
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size размер игровой доски
     */
    public void setSize(int size) {

        if (size < 3)
            throw new IllegalArgumentException("Минимальное значение size = 3");

        this.size = size;
        board = new BoardItem[size][size];

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                board[i][j] = BoardItem.UNDEFINED;

    }

    /**
     * Получить элемент игровой доски
     * @param i
     * @param j
     * @return элемент с индексом (i;j)
     */
    public BoardItem getItemAt(int i, int j) {
        return (i < size && j < size) ? board[i][j] : null;
    }

    /**
     * Установить элемент игровой доски
     * @param value элемент с индексом (i;j)
     * @param i
     * @param j
     */
    public void setItemAt(BoardItem value, int i, int j) {

        if (!(i < size && j < size))
            throw new IllegalArgumentException(
                    "Неверное значение индекса. Допустимо значение от 0 до "
                            + String.valueOf(size - 1));

        board[i][j] = value;
    }


    /**
     * Вычисление следующей выигрышной комбинации при текущем состоянии игровой доски.
     * Найденная комбинация на игровой доске отмечается как рассчитанная.
     *
     * @param winCount
     *            Длина линии из одинаковых символов (X или O) которая считается
     *            выигрышной комбинацией
     * @return Элемент того типа, из которых составлена выигрышная комбинация,
     *         null в случае отсутствия таковой
     */
    public BoardItem getNextWinner(int winCount) {

        if (winCount > size)
            throw new IllegalArgumentException("Длина выигрышной линии не может быть больше размера доски");

        BoardItem result = null;

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                result = findWin(i,j,winCount);
                if (result != null)
                    return result;
            }

        return result;
    }

    /**
     * Выполнить поиск выигрышной последовательности для элемента (i:j)
     *
     * @param i
     * @param j
     * @param winCount
     *            количество повторений элемента X или O выигрышной
     *            последовательности
     * @return Элемент того типа, из которых составлена выигрышная комбинация,
     *         null в случае отсутствия таковой
     */
    private BoardItem findWin(int i, int j, int winCount) {

        if (!board[i][j].equals(BoardItem.X) && !board[i][j].equals(BoardItem.O))
            return null;

        int cnti = i;
        int cntj = j;

        BoardItem result = null;

        winItems.clear();
        //поиск влево
        while (cntj >= 0) {
            result = searchAndReplace(i, j, i, cntj, winCount);
            cntj--;

            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;
        }

        cnti = i;
        winItems.clear();
        //поиск вниз
        while (cnti < size) {
            result = searchAndReplace(i, j, cnti, j, winCount);
            cnti++;

            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;

        }
        //поиск по диагоналям
        cnti = i; cntj = j;
        winItems.clear();
        while (cnti < size && cntj  >= 0) {

            result = searchAndReplace(i, j, cnti, cntj, winCount);
            cnti++;
            cntj--;

            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;

        }
        cnti = i; cntj = j;
        winItems.clear();
        while (cnti < size && cntj < size) {
            result = searchAndReplace(i, j, cnti, cntj, winCount);
            cnti++;
            cntj++;

            if (result != null && !result.equals(BoardItem.UNDEFINED))
                return result;
            else
            if (result == null)
                break;

        }

        return null;
    }

    /**
     * Выполнить сравнение элементов [i:j] [cnti:cntj] Если элементы совпадают -
     * добавить в список возможной выигрышной комбинации. Если комбинация
     * составлена отметить комбинацию на доске.
     *
     * @param i
     * @param j
     * @param cnti
     * @param cntj
     * @param winCount
     *            Количество элементов одного типа, линия из которых составляет
     *            выигрышную комбинацию
     * @return Если комбинация составлена - тип победителя (X или O). Если
     *         комбинация возможно будет составлена - BoardItem.UNDEFINED. Если
     *         комбинация не будет составлена - null
     */
    private BoardItem searchAndReplace(int i, int j, int cnti, int cntj,
                                       int winCount) {

        BoardItem item = board[cnti][cntj];

        if (item.equals(board[i][j])) {

            //список для возможной выигрышной комбинации - добавляем индекс элемента
            winItems.add(new Integer[] { cnti, cntj });

            //комбинация составлена
            if (winItems.size() == winCount) {
                // отмечаем выигрышные элементы
                for (Integer[] buff : winItems) {

                    board[buff[0]][buff[1]] = board[buff[0]][buff[1]].equals(BoardItem.X) ?
                            BoardItem.X_WIN_FIELD
                            : BoardItem.O_WIN_FIELD;
                }
                return item;
            }
        } else
            //комбинация не будет составлена
            return null;

        //комбинация возможно будет составлена
        return BoardItem.UNDEFINED;

    }

}