package com.example.servlet.factory.impl;

//import by.epam.chekun.domain.command.Command;
//import by.epam.chekun.domain.command.exception.CommandException;
//import by.epam.chekun.domain.command.factory.CommandFactory;
//import by.epam.chekun.domain.command.impl.GetMainPageCommandImpl;
//import by.epam.chekun.domain.command.impl.basket.AddProductToBasketCommand;
//import by.epam.chekun.domain.command.impl.basket.DeleteProductFromBasketCommand;
//import by.epam.chekun.domain.command.impl.basket.ViewUserBasketCommand;
//import by.epam.chekun.domain.command.impl.brand.*;
//import by.epam.chekun.domain.command.impl.category.*;
//import by.epam.chekun.domain.command.impl.order.*;
//import by.epam.chekun.domain.command.impl.paymentmethod.AddNewPaymentMethodCommand;
//import by.epam.chekun.domain.command.impl.paymentmethod.DeletePaymentMethodCommand;
//import by.epam.chekun.domain.command.impl.paymentmethod.ViewPaymentMethodTableCommand;
//import by.epam.chekun.domain.command.impl.product.AddNewProductCommand;
//import by.epam.chekun.domain.command.impl.product.DeleteProductCommand;
//import by.epam.chekun.domain.command.impl.product.EditProductCommand;
//import by.epam.chekun.domain.command.impl.product.ViewEditProductCommand;
//import by.epam.chekun.domain.command.impl.product.table.ViewCustomerProductTableCommand;
//import by.epam.chekun.domain.command.impl.product.table.ViewProductsTableCommand;
//import by.epam.chekun.domain.command.impl.product.table.ViewProductsWithCategoryAndBrandCommand;
//import by.epam.chekun.domain.command.impl.product.table.ViewProductsWithCategoryCommand;
//import by.epam.chekun.domain.command.impl.user.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static by.epam.chekun.domain.configuration.JspActionCommand.*;
//
//
//public class CommandFactoryImpl implements CommandFactory {
//
//    private static final CommandFactoryImpl instance = new CommandFactoryImpl();
//
//    public static CommandFactoryImpl getInstance() {
//        return instance;
//    }
//
//    private CommandFactoryImpl() {
//
//    }
//
//    @Override
//    public Command createCommand(final String action,
//                                 final HttpServletRequest request,
//                                 final HttpServletResponse response)
//            throws CommandException {
//
//        switch (action) {
//            case VIEW_SIGN_UP_WINDOW_COMMAND:
//                return new SignUpWindowCommand(request, response);
//            case SIGN_UP_COMMAND:
//                return new SignUpCommand(request, response);
//            case SIGN_IN_COMMAND:
//                return new SignInCommand(request, response);
//            case CHANGE_PASSWORD_COMMAND:
//                return new ChangePasswordUserCommand(request, response);
//            case LOGOUT_COMMAND:
//                return new LogoutCommand(request, response);
//            case VIEW_MAIN_PAGE_COMMAND:
//                return new GetMainPageCommandImpl(request, response);
//            case VIEW_USER_CABINET_COMMAND:
//                return new ViewUserCabinetCommand(request, response);
//            case VIEW_USERS_TABLE_COMMAND:
//                return new ViewUsersTableCommand(request, response);
//            case CHANGE_BAN_STATUS_COMMAND:
//                return new AdminChangeBanStatusCommand(request, response);
//            case CHANGE_USER_STATUS_COMMAND:
//                return new ChangeUserStatusAdminCommand(request, response);
//            case VIEW_USERS_TABLE_SORTED_COMMAND:
//                return new ViewUsersTableSortedCommand(request, response);
//            case EDIT_USER_COMMAND:
//                return new EditUserCommand(request, response);
//
//
//            ///////////////////////////////////////////////////////////////////////////
//            case ADD_NEW_CATEGORY_COMMAND:
//                return new AddNewCategoryCommand(request, response);
//            case VIEW_CATEGORIES_TABLE_COMMAND:
//                return new ViewCategoriesTableCommand(request, response);
//            case EDIT_CATEGORY_COMMAND:
//                return new EditCategoryCommand(request, response);
//            case VIEW_EDIT_CATEGORY_COMMAND:
//                return new ViewEditCategoryCommand(request, response);
//            case DELETE_CATEGORY_COMMAND:
//                return new DeleteCategoryCommand(request, response);
//            ///////////////////////////////////////////////////////////////////////////
//
//
//            ///////////////////////////////////////////////////////////////////////////
//            case VIEW_BRANDS_TABLE_COMMAND:
//                return new ViewBrandsTableCommand(request, response);
//            case VIEW_EDIT_BRAND_COMMAND:
//                return new ViewEditBrandCommand(request, response);
//            case EDIT_BRAND_COMMAND:
//                return new EditBrandCommand(request, response);
//            case ADD_NEW_BRAND_COMMAND:
//                return new AddNewBrandCommand(request, response);
//            case DELETE_BRAND_COMMAND:
//                return new DeleteBrandCommand(request, response);
//            ///////////////////////////////////////////////////////////////////////////
//
//            ///////////////////////////////////////////////////////////////////////////
//            case VIEW_PRODUCTS_TABLE_COMMAND:
//                return new ViewProductsTableCommand(request, response);
//            case ADD_NEW_PRODUCT_COMMAND:
//                return new AddNewProductCommand(request, response);
//            case DELETE_PRODUCT_COMMAND:
//                return new DeleteProductCommand(request, response);
//            case VIEW_EDIT_PRODUCT_COMMAND:
//                return new ViewEditProductCommand(request, response);
//            case EDIT_PRODUCT_COMMAND:
//                return new EditProductCommand(request, response);
//            ///////////////////////////////////////////////////////////////////////////
//
//
//            case VIEW_CUSTOMER_PRODUCT_TABLE_WITH_CATEGORY_AND_BRAND_COMMAND:
//                return new ViewProductsWithCategoryAndBrandCommand(request, response);
//
//            ///////////////////////////////////////////////////////////////////////////
//            case ADD_PRODUCT_TO_BASKET_COMMAND:
//                return new AddProductToBasketCommand(request, response);
//            case VIEW_USER_BASKET_COMMAND:
//                return new ViewUserBasketCommand(request, response);
//            case DELETE_PRODUCT_FROM_BASKET_COMMAND:
//                return new DeleteProductFromBasketCommand(request, response);
//
//            ///////////////////////////////////////////////////////////////////////////
//
//            case VIEW_PAYMENT_METHOD_TABLE_COMMAND:
//                return new ViewPaymentMethodTableCommand(request, response);
//            case ADD_NEW_PAYMENT_METHOD_COMMAND:
//                return new AddNewPaymentMethodCommand(request, response);
//            case DELETE_PAYMENT_METHOD_COMMAND:
//                return new DeletePaymentMethodCommand(request, response);
//
//            ///////////////////////////////////////////////////////////////////////////
//
//            ///////////////////////////////////////////////////////////////////////////
//            case ADD_NEW_ORDER_COMMAND:
//                return new AddNewOrderCommand(request, response);
//            case VIEW_ORDERS_HISTORY_COMMAND:
//                return new ViewOrdersHistoryCommand(request, response);
//            case VIEW_ORDER_DETAIL_COMMAND:
//                return new ViewOrderDetailCommand(request, response);
//            case CHANGE_ORDER_STATUS_COMMAND:
//                return new ChangeOrderStatusCommand(request, response);
//            case INVALIDATE_ORDER_COMMAND:
//                return new InvalidateOrderCommand(request, response);
//            case VIEW_ALL_ORDERS_COMMAND:
//                return new ViewAllOrdersHistoryCommand(request, response);
//            ///////////////////////////////////////////////////////////////////////////
//
//            case VIEW_CUSTOMER_PRODUCT_TABLE_COMMAND:
//                return new ViewCustomerProductTableCommand(request, response);
//            case VIEW_CUSTOMER_PRODUCT_TABLE_WITH_CATEGORY_COMMAND:
//                return new ViewProductsWithCategoryCommand(request, response);
//        }
//
//        throw new CommandException("No command with name " + action);
//    }
//
//
//}
