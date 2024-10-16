import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import { NavComponent } from './component/nav/nav.component';
import { MainComponent } from './component/main/main.component';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-root',
  standalone: true,
  template: `
    <app-header></app-header>
    <app-nav></app-nav>
    <router-outlet></router-outlet>
    <app-footer></app-footer>
  `,
  imports: [RouterOutlet, FooterComponent, HeaderComponent, NavComponent, MainComponent, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}
