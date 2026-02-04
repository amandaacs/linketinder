package com.project

import com.project.ui.Menu
import com.project.ui.MenuActions

static void main(String[] args) {
    Scanner scanner = new Scanner(System.in)

    while (true){
        Menu.show()
        print "Escolha uma opção: "
        int opt = scanner.nextInt()
        scanner.nextLine()
        MenuActions.handle(opt)
    }
}