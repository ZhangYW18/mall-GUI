package com.vampire.ui;

import javax.swing.*;
import javax.swing.table.*;

import utils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class Admin {
    private JPanel Admin;
    private JTabbedPane tabbedPane1;
    private JButton saveClient;
    private JButton deleteClient;
    private JButton addClient;
    private JButton exitButton;
    private JButton searchClient;
    private JTextField clientName;
    private JTextField clientAddress;
    private JTextField clientPhone;
    private JTextField clientEmail;
    private JTextField clientCompany;
    private JTable clientTable;
    private JButton showAllClient;
    private JButton showAllCommodity;
    private JButton addCommodity;
    private JButton deleteCommodity;
    private JButton saveCommodity;
    private JButton searchCommodity;
    private JTextField commodityPrice;
    private JTextField commodityBrand;
    private JTextField commodityNumber;
    private JTextField commodityName;
    private JTextField commodityCount;
    private JButton addCommodityCountButton;
    private JTextField commodityDescription;
    private JTextField addCommodityCount;
    private JTable commodityTable;
    private JFrame frame;

    private ClientModel clientModel;
    private CommodityModel commodityModel;

    private boolean isJTextFieldNull(JTextField jTextField, String name) {
        String nameText = jTextField.getText();
        if (nameText.length() <= 0) {
            JOptionPane.showMessageDialog(frame, name + "不能为空", "提示", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
        return false;
    }

    public Admin() {

        //       System.out.println("Login Success");


        clientModel = new ClientModel();
        clientTable.setModel(clientModel);

        commodityModel = new CommodityModel();
        commodityTable.setModel(commodityModel);

        //调整各列比例

        clientTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        clientTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        clientTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        clientTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        clientTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        clientTable.getColumnModel().getColumn(5).setPreferredWidth(120);

        clientTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        commodityPrice.setDocument(new DoubleDocument());   //商品价格是小数

        frame = new JFrame("客户订单管理系统 V1.0");
        frame.setContentPane(Admin);

        frame.setSize(1300, 700);
        UICommonUtils.makeFrameToCenter(frame);

        frame.setVisible(true);

        //客户电话只能纯数字
        clientPhone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

                } else {
                    e.consume(); //屏蔽掉非法输入
                }
            }
        });


        //增加客户
        //test passed 2019/4/6

        addClient.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {
                //判断姓名是否合法

                if (isJTextFieldNull(clientName, "姓名")) return;

                //判断电话是否合法
                String phoneText = clientPhone.getText();
                int len = phoneText.length();
                if (len != 0) {
                    if (len < 5 || len > 21) {
                        JOptionPane.showMessageDialog(frame, "电话位数应为6到20位", "提示", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }


                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", clientModel.getSize());
                map.put("name", clientName.getText());
                map.put("address", clientAddress.getText());
                map.put("email", clientEmail.getText());
                map.put("company", clientCompany.getText());
                map.put("phone", clientPhone.getText());

                clientModel.addRow(map);


            }
        });


        //删除客户
        //test passed 2019/4/6

        deleteClient.addActionListener(new ActionListener() {
            //          @Override
            public void actionPerformed(ActionEvent e) {

                if (clientTable.getSelectedRow() > -1) {
                    clientModel.remove(clientTable.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //查找客户
        //test passed 2019/4/6

        searchClient.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("name", clientName.getText());
                map.put("address", clientAddress.getText());
                map.put("email", clientEmail.getText());
                map.put("company", clientCompany.getText());
                map.put("phone", clientPhone.getText());

                clientModel.searchRow(map);

            }
        });

        //保存客户的修改
        //test passed 2019/4/6

        saveClient.addActionListener(new ActionListener() {
            //           @Override
            public void actionPerformed(ActionEvent e) {

                clientModel.save();

            }
        });

        //展示所有客户
        //test passed 2019/4/7

        showAllClient.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {

                clientModel.showAll();

            }
        });


        // Commodity Page

        //商品货号只能纯数字+字母
        //test passed 2019/4/7

        commodityNumber.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

                } else if (keyChar >= KeyEvent.VK_A && keyChar <= KeyEvent.VK_Z) {

                } else {
                    e.consume(); //屏蔽掉非法输入
                }
            }
        });

        //库存是整数
        //test passed 2019/4/7

        commodityCount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

                } else {
                    e.consume(); //屏蔽掉非法输入
                }
            }
        });

        //增加库存是整数
        //test passed 2019/4/7
        addCommodityCount.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

                } else {
                    e.consume(); //屏蔽掉非法输入
                }
            }
        });


        //增加商品
        //test passed 2019/4/7

        addCommodity.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {
                //判断是否非空
                if (isJTextFieldNull(commodityName, "姓名")) return;
                if (isJTextFieldNull(commodityNumber, "货号")) return;
                if (isJTextFieldNull(commodityCount, "库存")) return;
                if (isJTextFieldNull(commodityPrice, "价格")) return;

                //判断描述是否合法
                String descriptionText = commodityDescription.getText();
                int len = descriptionText.length();
                if (len != 0) {
                    if (len > 100) {
                        JOptionPane.showMessageDialog(frame, "描述过长，不能超过50个汉字", "提示", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("id", commodityNumber.getText());

                Map<String, Object> mp = commodityModel.commodityUtils.searchCommodityByID(commodityNumber.getText());
                if (mp.get("id") != null) {
                    JOptionPane.showMessageDialog(frame, "商品货号有重复", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                map.put("name", commodityName.getText());
                map.put("description", commodityDescription.getText());
                map.put("brand", commodityBrand.getText());
                map.put("price", Double.parseDouble(commodityPrice.getText()));
                map.put("count", Integer.parseInt(commodityCount.getText()));
                map.put("sold", 0);

                commodityModel.addRow(map);


            }
        });


        //删除商品
        //test passed 2019/4/7

        deleteCommodity.addActionListener(new ActionListener() {
            //          @Override
            public void actionPerformed(ActionEvent e) {

                if (commodityTable.getSelectedRow() > -1) {
                    commodityModel.remove(commodityTable.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //查找商品
        //test passed 2019/4/7

        searchCommodity.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", commodityNumber.getText());
                map.put("name", commodityName.getText());
                map.put("description", commodityDescription.getText());
                map.put("brand", commodityBrand.getText());
         /*       if (!commodityPrice.getText().equals(""))
                    map.put("price", Integer.parseInt(commodityPrice.getText()));
                if (!commodityCount.getText().equals(""))
                    map.put("count", Integer.parseInt(commodityCount.getText()));*/


                commodityModel.searchRow(map);

            }
        });

        //保存商品的修改
        //test passed 2019/4/7

        saveCommodity.addActionListener(new ActionListener() {
            //           @Override
            public void actionPerformed(ActionEvent e) {

                commodityModel.save();

            }
        });

        //增加商品库存
        //test passed 2019/4/7

        addCommodityCountButton.addActionListener(new ActionListener() {
            //          @Override
            public void actionPerformed(ActionEvent e) {
                if (isJTextFieldNull(addCommodityCount, "增加库存数量")) return;
                if (commodityTable.getSelectedRow() > -1) {
                    commodityModel.addCommodityCount(commodityTable.getSelectedRow(),
                            Integer.parseInt(addCommodityCount.getText()));
                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //展示所有商品
        //test passed 2019/4/7
        showAllCommodity.addActionListener(new ActionListener() {
            //            @Override
            public void actionPerformed(ActionEvent e) {

                commodityModel.showAll();

            }
        });

        //退出
        //test passed 2019/4/6
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
            }
        });


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private class ClientModel extends AbstractTableModel {


        ClientUtils clientUtils = new ClientUtils();
        List<Map<String, Object>> clientList = clientUtils.findAllClient();
        private int size = clientList.size();

        String[] tableStrings = {"id", "name", "address", "phone", "email", "company"};
        String[] showStrings = {"编号", "姓名", "地址", "电话", "Email地址", "公司"};

        public void addRow(Map<String, Object> row) {
            clientUtils.addClient(row);
            clientList.add(row);
            size++;
            fireTableDataChanged();
        }

        public void searchRow(Map<String, Object> row) {
            clientList = clientUtils.searchClient(row);
            fireTableDataChanged();
        }

        public void save() {

            int sz = clientList.size();
            for (int i = 0; i < sz; i++) {
                Map<String, Object> map = clientList.get(i);
                clientUtils.saveClient(map);
            }
            for (int i = 0; i < sz; i++) {
                int id = Integer.parseInt(clientList.get(i).get("id").toString());
                clientList.set(i, clientUtils.searchClientByID(id));
            }
            fireTableDataChanged();

        }


        public void remove(int rowIndex) {
            //             int id = Integer.parseInt(map.get("id").toString());
            int id = Integer.parseInt(clientList.get(rowIndex).get("id").toString());
            clientUtils.removeClient(id);
            for (int i = id + 1; i < size; i++) {
                clientUtils.decClientID(i);
            }

            clientList.remove(rowIndex);
            int sz = clientList.size();
            for (int i = 0; i < sz; i++) {
                int nowId = Integer.parseInt(clientList.get(i).get("id").toString());
                clientList.set(i, clientUtils.searchClientByID(nowId));
            }
            size--;
            fireTableDataChanged();
        }

        public void showAll() {
            clientList = clientUtils.findAllClient();
            fireTableDataChanged();
        }

        public int getSize() {
            return size;
        }

        //  @Override
        public int getRowCount() {
            return clientList.size();
        }

        //   @Override
        public int getColumnCount() {
            return tableStrings.length;
        }

        //   @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = clientList.get(rowIndex);
            return map.get(tableStrings[columnIndex]);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = clientList.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }

        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

    }

    private class CommodityModel extends AbstractTableModel {


        CommodityUtils commodityUtils = new CommodityUtils();
        List<Map<String, Object>> commodityList = commodityUtils.findAllCommodity();
        private int size = commodityList.size();

        String[] tableStrings = {"id", "name", "description", "brand", "price", "count", "sold"};
        String[] showStrings = {"货号", "名称", "描述", "品牌", "价格(￥)", "库存", "已卖出"};

        public void addRow(Map<String, Object> row) {
            commodityUtils.addCommodity(row);
            commodityList.add(row);
            size++;
            fireTableDataChanged();
        }

        public void searchRow(Map<String, Object> row) {
            commodityList = commodityUtils.searchCommodity(row);
            fireTableDataChanged();
        }


        public void save() {

            int sz = commodityList.size();
            for (int i = 0; i < sz; i++) {
                Map<String, Object> map = commodityList.get(i);
                commodityUtils.saveCommodity(map);
            }
            for (int i = 0; i < sz; i++) {
                String id = commodityList.get(i).get("id").toString();
                commodityList.set(i, commodityUtils.searchCommodityByID(id));
            }
            fireTableDataChanged();

        }


        public void remove(int rowIndex) {
            //             int id = Integer.parseInt(map.get("id").toString());
            String id = commodityList.get(rowIndex).get("id").toString();
            commodityUtils.removeCommodity(id);

            commodityList.remove(rowIndex);
            size--;
            fireTableDataChanged();
        }

        public void addCommodityCount(int rowIndex, int addCount) {
            String id = commodityList.get(rowIndex).get("id").toString();
            commodityUtils.addCommodityCountByID(id, addCount);
            commodityList.set(rowIndex, commodityUtils.searchCommodityByID(id));
            fireTableDataChanged();
        }

        public void showAll() {
            commodityList = commodityUtils.findAllCommodity();
            fireTableDataChanged();
        }

        public int getSize() {
            return size;
        }

        //  @Override
        public int getRowCount() {
            return commodityList.size();
        }

        //   @Override
        public int getColumnCount() {
            return tableStrings.length;
        }

        //   @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = commodityList.get(rowIndex);
            return map.get(tableStrings[columnIndex]);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0 && columnIndex != 6;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = commodityList.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }

        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Admin = new JPanel();
        Admin.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        Admin.putClientProperty("html.disable", Boolean.FALSE);
        tabbedPane1 = new JTabbedPane();
        Admin.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 11, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("客户管理", panel1);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 11, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        clientTable = new JTable();
        scrollPane1.setViewportView(clientTable);
        final JLabel label1 = new JLabel();
        label1.setText("电话");
        panel1.add(label1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("邮件");
        panel1.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("姓名*");
        panel1.add(label3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clientName = new JTextField();
        panel1.add(clientName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clientPhone = new JTextField();
        panel1.add(clientPhone, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clientEmail = new JTextField();
        panel1.add(clientEmail, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        clientCompany = new JTextField();
        panel1.add(clientCompany, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addClient = new JButton();
        addClient.setText("增加客户");
        panel1.add(addClient, new com.intellij.uiDesigner.core.GridConstraints(1, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteClient = new JButton();
        deleteClient.setText("删除客户");
        panel1.add(deleteClient, new com.intellij.uiDesigner.core.GridConstraints(1, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveClient = new JButton();
        saveClient.setText("保存当前表格");
        panel1.add(saveClient, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchClient = new JButton();
        searchClient.setText("查询客户");
        panel1.add(searchClient, new com.intellij.uiDesigner.core.GridConstraints(2, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("*为必填项");
        panel1.add(label4, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("地址");
        panel1.add(label5, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clientAddress = new JTextField();
        panel1.add(clientAddress, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        showAllClient = new JButton();
        showAllClient.setText("显示所有客户");
        panel1.add(showAllClient, new com.intellij.uiDesigner.core.GridConstraints(0, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("点击表格修改客户,修改后记得保存哦");
        panel1.add(label6, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("公司");
        panel1.add(label7, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 14, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPane1.addTab("商品管理", panel2);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel2.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 14, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        commodityTable = new JTable();
        scrollPane2.setViewportView(commodityTable);
        final JLabel label8 = new JLabel();
        label8.setText("价格 *");
        panel2.add(label8, new com.intellij.uiDesigner.core.GridConstraints(2, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commodityDescription = new JTextField();
        panel2.add(commodityDescription, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 7, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        commodityPrice = new JTextField();
        panel2.add(commodityPrice, new com.intellij.uiDesigner.core.GridConstraints(2, 9, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addCommodity = new JButton();
        addCommodity.setText("增加商品");
        panel2.add(addCommodity, new com.intellij.uiDesigner.core.GridConstraints(1, 11, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteCommodity = new JButton();
        deleteCommodity.setText("删除商品");
        panel2.add(deleteCommodity, new com.intellij.uiDesigner.core.GridConstraints(1, 12, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveCommodity = new JButton();
        saveCommodity.setText("保存当前表格");
        panel2.add(saveCommodity, new com.intellij.uiDesigner.core.GridConstraints(2, 11, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchCommodity = new JButton();
        searchCommodity.setText("查询商品");
        panel2.add(searchCommodity, new com.intellij.uiDesigner.core.GridConstraints(2, 12, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showAllCommodity = new JButton();
        showAllCommodity.setText("显示所有商品");
        panel2.add(showAllCommodity, new com.intellij.uiDesigner.core.GridConstraints(0, 12, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("/个");
        panel2.add(label9, new com.intellij.uiDesigner.core.GridConstraints(2, 10, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("￥");
        panel2.add(label10, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commodityBrand = new JTextField();
        panel2.add(commodityBrand, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        commodityName = new JTextField();
        panel2.add(commodityName, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("品牌");
        panel2.add(label11, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("货号*");
        panel2.add(label12, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commodityNumber = new JTextField();
        panel2.add(commodityNumber, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("名称*");
        panel2.add(label13, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("(数字+字母)");
        panel2.add(label14, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("描述");
        panel2.add(label15, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("点击表格修改商品,修改后记得保存哦");
        panel2.add(label16, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("*为必填项");
        panel2.add(label17, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("库存*");
        panel2.add(label18, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        commodityCount = new JTextField();
        panel2.add(commodityCount, new com.intellij.uiDesigner.core.GridConstraints(2, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addCommodityCountButton = new JButton();
        addCommodityCountButton.setText("增加库存");
        panel2.add(addCommodityCountButton, new com.intellij.uiDesigner.core.GridConstraints(0, 11, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addCommodityCount = new JTextField();
        addCommodityCount.setText("");
        panel2.add(addCommodityCount, new com.intellij.uiDesigner.core.GridConstraints(0, 8, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("增加库存");
        panel2.add(label19, new com.intellij.uiDesigner.core.GridConstraints(0, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label20 = new JLabel();
        label20.setText("个");
        panel2.add(label20, new com.intellij.uiDesigner.core.GridConstraints(0, 10, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        Admin.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("退出");
        Admin.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label21 = new JLabel();
        label21.setText("欢迎");
        Admin.add(label21, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Admin;
    }

}
